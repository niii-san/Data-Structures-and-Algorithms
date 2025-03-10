package __practice;

public class Q10_MaxArrElement {

    static int maxElement(int[] arr){
        if(arr.length==0) return -1;

        int maxElement = arr[0];

        for(int i =1;i < arr.length;i++){
            if(maxElement<arr[i]){
                maxElement=arr[i];
            }
        }
        return maxElement;
    }

    public static void main(String[] args) {

        int[] arr = {5,20,10,12,44,34,40,66};
        System.out.println(maxElement(arr));

        
    }
}
