class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n=nums.length;
        int pre=-1;
        int count=0;
        int result=0;
        int i=0;
        while(i<n){
            int value=nums[i];
            if(value==1){
                if(pre==value){
                    count++;
                }else{
                    count=1;
                }
            }
            else{
                count=0;
            }
            result=Math.max(result,count);
            pre=nums[i];
            i++;
        }
    return result;    
    }
}