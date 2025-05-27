class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        HashMap<Integer,ArrayList<Integer>> graph = new HashMap<>();
        int[] indegree = new int[numCourses];
        for(int[] prerequisit : prerequisites){
            graph.putIfAbsent(prerequisit[0], new ArrayList<>());
            graph.get(prerequisit[0]).add(prerequisit[1]);
            indegree[prerequisit[1]]++;
        }
        
        int visited = 0,i,node;
        Queue<Integer> visiting = new LinkedList<>();
        for(i = 0; i < numCourses; i++){
            if(indegree[i] == 0)    visiting.offer(i);
        }
        int[] result = new int[numCourses];
        i = numCourses-1;
        while(!visiting.isEmpty()){
            node = visiting.poll();
            visited++;
            result[i--] = node;
            if(graph.containsKey(node)){
                for(int neighbor : graph.get(node)){
                    indegree[neighbor]--;
                    if(indegree[neighbor] == 0) visiting.offer(neighbor);
                }
            }
        }
        return visited == numCourses ? result : new int[]{};
    }
}