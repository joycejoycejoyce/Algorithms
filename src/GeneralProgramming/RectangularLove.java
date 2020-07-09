package GeneralProgramming;

public class RectangularLove {
    public static class Rectangle {
        private int leftX;
        private int bottomY;

        private int width;
        private int height;

        public Rectangle(){}

        public Rectangle(int leftX, int bottomY, int width, int height){
            this.leftX = leftX;
            this.bottomY = bottomY;
            this.width  = width;
            this.height = height;
        }

        public int getLeftX() {
            return leftX;
        }
        public int getBottomY() {
            return bottomY;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        @Override
        public String toString() {
            return String.format("(left: %d, bottom: %d, width: %d, height: %d)",
                    leftX, bottomY, width, height);
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Rectangle)) {
                return false;
            }
            final Rectangle r = (Rectangle) o;
            return leftX == r.leftX && bottomY == r.bottomY
                    && width == r.width && height == r.height;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = result * 31 + leftX;
            result = result * 31 + bottomY;
            result = result * 31 + width;
            result = result * 31 + height;
            return result;
        }
    }


    public static Rectangle intuitiveApproach(Rectangle rect1, Rectangle rect2) {

        /*
        * we can get the height and width from the same method,
        * so we create a helper method
        * */

        // put the width in to check
        int rec1WidthStartPt = rect1.getLeftX();
        int rec2WidthStartPt = rect2.getLeftX();
        int rec1HeightStartPt = rect1.getBottomY();
        int rec2HeightStartPt = rect2.getBottomY();

        int rec1Width = rect1.getWidth();
        int rec2Width = rect2.getWidth();
        int rec1Height = rect1.getHeight();
        int rec2Height = rect2.getHeight();

        int[] recWidthRange = intuitiveApproachHelper(rec1WidthStartPt, rec1Width, rec2WidthStartPt, rec2Width);
        int[] recHeightRange = intuitiveApproachHelper(rec1HeightStartPt, rec1Height, rec2HeightStartPt, rec2Height);

        if(recWidthRange == null || recHeightRange == null){
            return new Rectangle();
        }

        return new Rectangle(recWidthRange[0],  recHeightRange[0], recWidthRange[1], recHeightRange[1]);
    }

    public static int[] intuitiveApproachHelper(int startPt1, int width1, int startPt2, int width2){
        int rangeStart = Math.max(startPt1, startPt2);
        int rangeEnd = Math.min(startPt1 + width1, startPt2 + width2);
        int range = rangeEnd - rangeStart;

        return rangeEnd <= rangeStart ? null : new int[]{rangeStart, range};
    }


    // optimized way
    private static class RangeOverlap{
        private int startPoint;
        private int length;

        public RangeOverlap(int startPoint, int length) {
            this.startPoint = startPoint;
            this.length = length;
        }

        public int getStartPoint() {
            return startPoint;
        }

        public int getLength() {
            return length;
        }
    }

    private static RangeOverlap findRangeOverlap(int startPoint1, int length1, int startPoint2, int length2){
        int highestStartPoint = Math.max(startPoint1, startPoint2);
        int lowestEndPoint = Math.min(startPoint1 + length1, startPoint2 + length2);

        // if the two rectangles don't overlap
        if (highestStartPoint >= lowestEndPoint){
            return new RangeOverlap(0, 0);
        }
        // if the two rectangles overlap
        // compute the length
        int length = highestStartPoint - lowestEndPoint;
        return new RangeOverlap(highestStartPoint, length);
    }

    private static Rectangle optimizedApproach(Rectangle rect1, Rectangle rect2){
        // get the overlapped width
        RangeOverlap xOverlap = findRangeOverlap(rect1.getLeftX(), rect1.getWidth(),
                                                rect2.getLeftX(), rect2.getWidth());
        RangeOverlap yOverlap = findRangeOverlap(rect1.getBottomY(), rect1.getHeight(),
                                                rect2.getBottomY(), rect2.getHeight());
        if (xOverlap.getLength() == 0 || yOverlap.getLength() == 0){
            return new Rectangle();
        }
        return new Rectangle(xOverlap.getStartPoint(), yOverlap.getStartPoint(),
                                xOverlap.getLength(), yOverlap.getLength());
    }

    /* Complexity:
            Time complexity: O(1)
            Space complexity: O(1)

            If we take a closer look,  every time a method is called,
                the input size is consistent.
            The input is ALWAYS 2 rectangles.

            And the operations are consistent.
     */



    public static void main(String[] args) {
        // test case 1
        final Rectangle actual = optimizedApproach (
                new Rectangle(1, 1, 6, 3), new Rectangle(5, 2, 3, 6));
        final Rectangle expected = new Rectangle (5, 2, 2, 2);
        System.out.println("expected: "+ expected.toString());
        System.out.println("actual:: "+ actual.toString());


        // test case 2
        final Rectangle actual2 = optimizedApproach(
                new Rectangle(1, 1, 6, 6), new Rectangle(3, 3, 2, 2));
        final Rectangle expected2 = new Rectangle(3, 3, 2, 2);
        System.out.println("expected: "+ expected2.toString());
        System.out.println("actual:: "+ actual2.toString());
    }


}
