package BitManipulation;

public class TheStolenBreakfastDrone {
    public static int findUniqueDeliveryId(int[] deliveryIds){
        int uniqueDeliveryId = 0;

        for (int deliveryId: deliveryIds){
            uniqueDeliveryId ^= deliveryId;
        }

        return uniqueDeliveryId;
    }


    public static void main(String[] args) {
        findUniqueDeliveryId(new int[] {1});

        findUniqueDeliveryId(new int[] {1, 2, 2});

        findUniqueDeliveryId(new int[] {3, 3, 2, 2, 1});

        findUniqueDeliveryId(new int[] {3, 2, 1, 2, 3});

        findUniqueDeliveryId(new int[] {2, 5, 4, 8, 6, 3, 1, 4, 2, 3, 6, 5, 1});
    }
}
