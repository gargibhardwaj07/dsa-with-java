class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();

        backtrack(candidates, target, 0, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(int[] nums, int target, int index,
                           List<Integer> current,
                           List<List<Integer>> result){

        if(target == 0){
            result.add(new ArrayList<>(current));
            return;
        }

        if(target < 0 || index >= nums.length){
            return;
        }

        // choose current number
        current.add(nums[index]);
        backtrack(nums, target - nums[index], index, current, result);

        // skip current number
        current.remove(current.size() - 1);
        backtrack(nums, target, index + 1, current, result);
    }
}