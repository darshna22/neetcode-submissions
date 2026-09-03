class Solution {
    public int removeElement(int[] nums, int val) {
        int n=nums.length;
        int t=val;
        int result=0;
        int i=0;
        int k=0;
        while(i<n){
            int value=nums[i];
            if(value!=t){
               nums[k]=value;
               k++; 
               result++;
            }
            i++;
        }
    return result;    
    }
}