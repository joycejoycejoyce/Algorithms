package SystemDesign;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class FindDuplicateFiles {

    public static class FilePaths {
        private Path duplciatePath;
        private Path originalPath;

        public FilePaths(Path duplciatePath, Path originalPath){
            this.duplciatePath = duplciatePath;
            this.originalPath = originalPath;
        }

        public Path getDuplciatePath(){
            return duplciatePath;
        }

        public Path getOriginalPath(){
            return originalPath;
        }

        public String toString(){
            return String.format("(duplicate %s, original %s)", duplciatePath, originalPath);
        }
    }

    private static class FileInfo {
        long timeLastEdited;
        Path path;

        FileInfo(long timeLastEdited, Path path){
            this.timeLastEdited = timeLastEdited;
            this.path = path;
        }
    }


    /* 1. initialization
            hash map: hold the files we've already seen
            stack: directories and files as we go through them
            list: hold output FilePaths objects
     */

    public static List<FilePaths> findDuplicateFiles(Path startingDirectory){
        Map<String, FileInfo> filesSeenAlready = new HashMap<>();
        Deque<Path> stack = new ArrayDeque<>();
        stack.push(startingDirectory);

        List<FilePaths> duplicates = new ArrayList<>();

        while (!stack.isEmpty()){
            Path currentPath = stack.pop();
            File currentFile = currentPath.toFile();

            // if it's a directory
            // put the contents in our stack
            if(currentFile.isDirectory()){
                for (File file : currentFile.listFiles()){
                    stack.push(file.toPath());
                }
                // if it is a file
            }else{
                // get its contents
                String fileContents = null;
                try{
                    byte[] encodedFile = Files.readAllBytes(currentPath);
                    fileContents = new String(encodedFile, "UTF-8");
                }catch (IOException e){
                    // show error and skip this file
                    System.out.println(e);
                    continue;
                }
                // get its last edited time
                long currentLastEditedTime = currentFile.lastModified();
                // if we've seen it before
                if (filesSeenAlready.containsKey(fileContents)){
                    FileInfo existingFileInfo = filesSeenAlready.get(fileContents);

                    if (currentLastEditedTime > existingFileInfo.timeLastEdited){
                        // current file is the dupe !
                        duplicates.add(new FilePaths(currentPath, existingFileInfo.path));
                    }else{
                        // old file is the dupe!
                        duplicates.add(new FilePaths(existingFileInfo.path, currentPath));

                        // but also update filesSeenAlready to have the new file's info
                        filesSeenAlready.put(fileContents, new FileInfo(currentLastEditedTime, currentPath));
                    }
                    // if it's a new file, throw it in filesSeenAlready
                    // and record its path and last edited time
                    // so we can tell later if it's a dupe
                }else{
                    filesSeenAlready.put(fileContents, new FileInfo(currentLastEditedTime, currentPath));
                }
            }
            return duplicates;
        }
        return duplicates;
    }


    /**
     * we take a fingerprint of each file in constant time by hashing the first few, middle few,
     * and last few bytes.
     *
     * We store each file's fingerprint in a hash map as we go
     *
     * If a given file's fingerprint is already in our hash map, we assume we have a duplicate. In
     * that case, we assume the file edited most recently is the one created by our friend
     *
     */
    public static List<FilePaths> optimizedApproach(Path startingDirectory){
        Map<String, FileInfo> filesSeenAlready = new HashMap<>();
        Deque<Path> stack = new ArrayDeque<>();
        stack.push(startingDirectory);

        List<FilePaths> duplicates = new ArrayList<>();

        while (!stack.isEmpty()){
            Path currentPath = stack.pop();
            File currentFile = new File(currentPath.toString());

            // if it's a directory,
            // put the contents in our stack
            if (currentFile.isDirectory()){
                for (File file : currentFile.listFiles()){
                    stack.push(file.toPath());
                }
            }else{
                String fileHash;
                try{
                    fileHash = sampleHashFile(currentPath);
                }catch (IOException | NoSuchAlgorithmException e ){
                    // show error and skip this file
                    e.printStackTrace();
                    continue;
                }

                // get its last edited time
                long currentLastEditedTime = currentFile.lastModified();

                // if we've seen it before
                if (filesSeenAlready.containsKey(fileHash)){
                    FileInfo fileInfo = filesSeenAlready.get(fileHash);
                    long existingLastEditedTime = fileInfo.timeLastEdited;
                    Path existingPath = fileInfo.path;

                    if (currentLastEditedTime > existingLastEditedTime){
                        // current file is the dupe
                        duplicates.add(new FilePaths(currentPath, existingPath));
                    }else{
                        duplicates.add(new FilePaths(existingPath, currentPath));
                        // but also update filesSeenAlready to have the new file's info
                        filesSeenAlready.put(fileHash, new FileInfo(currentLastEditedTime, currentPath));
                    }
                }else{
                    filesSeenAlready.put(fileHash, new FileInfo(currentLastEditedTime, currentPath));
                }
            }
        }
        return duplicates;
    }
    /*

        Complexity:

            n: number of files on the file system

            Time: O(n)

            Space: O(n)

            If we add the last-minute check to see if two files with the same fingerprints are actually
            the same files, then in the worst case all the files are the same and we have to read their full
            contents to confirm this, giving us a runtime that's order of the total size of our files on disc
        Assumptions:
            * Two different files won't have the same fingerprints.

            * The most recently edited file is the duplicate: it might be wrong. For example, there might
                be files which have been edited by daemons (programs that run in the background) after
                our friend finished duplicating them.
            * Two files with the same contents are the same file. Not really true. For example, we might have
                empty files in multiple places in our file system that aren't duplicates of each other.

            Given these potential issues, we definitely want a human to confirm before we delete any files.

        Some ideas for further improvements:
            1. Some file systems also keep track of when a file was created. If your filesystem supports
               this, you could use this as a potentially strong heuristic for telling which of two copies of
               a file is the dupe
            2. When you do compare full file contents to ensure two files are the same, no need to read
                the entire files into memory. Open both files and read them one block at a time. You can
                short-circuit as soon as you find two blocks that don't match, and you only ever need to
                store a couple blocks in memory.



     */
    private static final int SAMPLE_SIZE = 4000;

    private static String sampleHashFile(Path path) throws IOException, NoSuchAlgorithmException{
        final long totalBytes = new File(path.toString()).length();
        try(InputStream inputStream = new FileInputStream(path.toString())){
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            DigestInputStream digestInputStream = new DigestInputStream(inputStream, digest);

        // if the file is too short to take 3 samples, hash the entire file
            if(totalBytes < SAMPLE_SIZE * 3){
                byte[] bytes = new byte[(int) totalBytes];
                digestInputStream.read(bytes);
            }else{
                byte[] bytes = new byte[SAMPLE_SIZE*3];
                long numBytesBetweenSamples = (totalBytes - SAMPLE_SIZE * 3) / 2;

                // read first, middle and last bytes
                for (int i=0; i<3;i++){
                    digestInputStream.read(bytes, i*SAMPLE_SIZE, SAMPLE_SIZE);
                    digestInputStream.skip(numBytesBetweenSamples);
                }
            }
            return new BigInteger(1, digest.digest()).toString(16);
        }
    }
}

/* Complexity:

    b: total amt of space taken up by all the files on the file system

    Time: O(b)
    Space: O(b)

 */
