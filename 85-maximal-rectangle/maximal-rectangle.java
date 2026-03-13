class Solution {
    public int maximalRectangle(char[][] matrix) {
       if (matrix.length == 0) return 0;
        
        int m = matrix.length, n = matrix[0].length;
        int[] heights = new int[n];
        int max = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                heights[j] = matrix[i][j] == '1' ? heights[j] + 1 : 0;
            }
            max = Math.max(max, largestRectangleArea(heights));
        }
        
        return max;
    }
    
    private int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        
        for (int i = 0; i <= heights.length; i++) {
            int h = (i == heights.length) ? 0 : heights[i];
            
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, height * width);
            }
            
            stack.push(i);
        }
        
        return max;  
    }
}