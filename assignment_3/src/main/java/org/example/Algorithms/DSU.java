package org.example.Algorithms;

public class DSU {
    private int[] root;
    private int[] rank;

    public DSU(int size){
        root = new int[size];
        rank = new int[size];
        for(int i=0;i<size; i++){
            root[i] = i;
        }
    }
    public int find(int x){
        if(root[x]!=x){
            root[x] = find(root[x]);
        }
        return root[x];
    }

    public void union(int x, int y){
        int rootX = find(x), rootY = find(y);
        if(rootX == rootY) return;

        if(rank[rootX]<rank[rootY]) root[rootX] = rootY;
        else if(rank[rootX]>rank[rootY]) root[rootY] = rootX;
        else{
            root[rootY] = rootX;
            rank[rootX]++;
        }
    }
}
