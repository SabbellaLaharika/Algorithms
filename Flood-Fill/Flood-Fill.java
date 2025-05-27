class Solution {
    void dfs(int[][] image,int x,int y, int oldColor, int newColor){
        int m = image.length,n = image[0].length;
        if(x < 0 || x >= m || y < 0 || y >= n || image[x][y] != oldColor)   return;
        image[x][y] = newColor;
        dfs(image,x+1,y,oldColor,newColor);
        dfs(image,x-1,y,oldColor,newColor);
        dfs(image,x,y+1,oldColor,newColor);
        dfs(image,x,y-1,oldColor,newColor);
    }
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if(image[sr][sc] == color)  return image;
        int oldColor = image[sr][sc];
        dfs(image,sr,sc,oldColor,color);
        return image;
    }
}