class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n=nums.length;
        int pre=-1;
        int count=0;
        int result=0;
        int i=0;
        while(i<n){
            if(nums[i]==1){
                if(pre==nums[i]){
                    count++;
                }else{
                    count=1;
                }
            }
            else{
                count=0;
            }
            result=Math.max(result,count);
            System.out.println("max="+result);
            pre=nums[i];
            i++;
        }
    return result;    
    }
}