class Solution {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        int[] indegree = new int[n];
        for(int[] edge : edges){
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);
            indegree[edge[1]]++;
        }
        int i,node;
        List<Set<Integer>> ancestor = new ArrayList<>();
        Queue<Integer> visiting = new LinkedList<>();
        for(i = 0; i < n; i++) {
            ancestor.add(new HashSet<>());
            if(indegree[i] == 0)    visiting.add(i);
        } 
        while(!visiting.isEmpty()){
            node = visiting.poll();
            if(graph.containsKey(node)){
                for(int neighbour : graph.get(node)){
                    ancestor.get(neighbour).addAll(ancestor.get(node));
                    ancestor.get(neighbour).add(node);
                    indegree[neighbour]--;
                    if(indegree[neighbour] == 0)    visiting.add(neighbour);
                }
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for(Set<Integer> s : ancestor){
            ArrayList<Integer> ele = new ArrayList<>(s);
            Collections.sort(ele);
            result.add(ele);
        }
        return result;
    }
}