class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegree = new int[n];
        int[][] dp = new int[n][26]; // DP table for color frequency tracking
        HashSet<Integer> colorsAvailable = new HashSet<>();
        for(char color : colors.toCharArray())  colorsAvailable.add(color - 'a');
        // Build the graph and indegree array
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            indegree[edge[1]]++;
        }
        
        // Initialize queue for topological sorting
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int visited = 0, maxColorValue = 0;
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited++;
            int colorIndex = colors.charAt(node) - 'a';
            dp[node][colorIndex]++;  // Increment count for this node's color
            maxColorValue = Math.max(maxColorValue, dp[node][colorIndex]);

            // Process neighbors
            if (graph.containsKey(node)) {
                for (int neighbor : graph.get(node)) {
                    for (int i : colorsAvailable) {
                        dp[neighbor][i] = Math.max(dp[neighbor][i], dp[node][i]);
                    }
                    indegree[neighbor]--;
                    if (indegree[neighbor] == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
        }
        
        return visited == n ? maxColorValue : -1;
    }
}