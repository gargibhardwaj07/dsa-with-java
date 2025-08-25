class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        //declare two pointers left and right
        //left is in last in arr1 and right in start of array2
        int left = m-1;
        int right = 0;

        //jb tk arr[left] bda h arr[right s swap kr denge]
        while(left >= 0 && right< n){
            if(nums1[left] > nums2[right]){
            int temp = nums1[left];
            nums1[left] = nums2[right];
            nums2[right] = temp;
            
            left --; //kyuki left last m he h aab peeche le jynge
            right++;
            }else{
                break;
            }
        }        

        Arrays.sort(nums1, 0 , m);
        Arrays.sort(nums2);

       // / copy nums2 into nums1 starting from index m
        for (int i = 0; i < n; i++) {
            nums1[m + i] = nums2[i];
        }
    }
}