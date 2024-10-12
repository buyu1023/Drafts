package org.example;

/**
 * @author buyu_6911
 * @version V1.0
 * @Package org.example
 * @date 2024/4/7 23:03
 */
    public class UF {
        // 实现一个并查集api
        // 记录连通分量(不连通的集合数)
        private int count;
        // 记录每个节点的父节点
        private int[] parent;
        // 按秩合并所需 记录树的节点数量
        /**
         * 对于实现了路径压缩的find方法
         * 每一次调用find会使得路径上的所有节点直接连接至根节点
         * */
        private int[] size;

        // 构造器
        public UF(int n) {
            // 开始时均不连通
            this.count = n;
            // 初始化每个节点的父节点均为自己
            // 初始化时每个几点的秩为1
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        // union方法
        public void union(int p, int q) {
            int rootp = find(p);
            int rootq = find(q);
            if (rootq == rootp) {
                return;
            }
            // 实现按秩合并
            if (size[rootp] > size[rootq]) {
                parent[q] = rootp;
                size[rootp] += size[rootq];
            } else {
                parent[p] = rootq;
                size[rootq] += size[rootp];
            }
            // 每次成功合并都会导致连通分量的降低
            count--;
        }
        // find方法 找到根节点
        public int find(int p) {
            // 重点是实现路径压缩 通过巧妙的递归
            if (parent[p] != p){
                // 注意这里的递归 按照函数的定义 find会找到当前节点的根节点
                // 巧妙之处在于 find方法在寻找根节点时，遍历路径上的所有节点
                // 重点是这些find方法均具有相同的返回值
                // 这样就使得路径上的所有节点均直接连接到原本的根节点上
                parent[p] = find(parent[p]);
            }
            return parent[p];
        }


    }
