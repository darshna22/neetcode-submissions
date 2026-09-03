class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        PriorityQueue<Pair<Integer,Integer>> pq =new PriorityQueue<>((a,b)->b.getValue()-a.getValue());
        Map<Integer,Integer> map=new HashMap<>();
        int[] result=new int[k];
        int i=0;
        int n=nums.length;
        while(i<n){
            int key=nums[i];
            map.put(key, map.getOrDefault(key,0)+1);
            i++;
        }
        for(Integer key:map.keySet()){
            pq.add(new Pair(key,map.get(key)));
        }

        int index=0;
        while(k>0){
            result[index]=pq.poll().getKey();
            k--;
            index++;
        }
     return result;   
    }
}
