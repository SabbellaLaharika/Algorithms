class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if(image[sr][sc] == color)  return image;
        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        int r = image.length,c = image[0].length,x,y;
        int oldColor = image[sr][sc];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sr,sc});  // 1, 2
        image[sr][sc] = color;
        while(!q.isEmpty()){
            int[] front = q.poll(); 
            for(int[] direction : directions){
                x = front[0]+direction[0];
                y = front[1]+direction[1];
                if(x >= 0 && x < r && y >= 0 && y < c && image[x][y] == oldColor){
                    image[x][y] = color;
                    q.add(new int[]{x,y});
                }
            }
        }
        return image;
    }
}