using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using QuickGraph;
using QuickGraph.Algorithms;
using QuickGraph.Data;

namespace FAS
{
    public class GraphGenerator
    {
        // n - the number of vertices, f - min FAS, m - lower bound of the total number of arcs
        public BidirectionalGraph<int, Edge<int>> generate(int n, int f, int m)
        {
            Random rnd = new Random();
            List<int> p = Enumerable.Range(1, n).OrderBy(x => rnd.Next()).ToList<int>();
            int num_arcs = 0;
            int i, j;
            BidirectionalGraph<int, Edge<int>> graph = new BidirectionalGraph<int, Edge<int>>(false);
            graph.AddVertexRange(Enumerable.Range(1, n));

            bool fl = false;

            for (int d = 0; d < f; d++)
            {
                i = rnd.Next(2, n + 1);
                j = rnd.Next(1, i);
                if (i == j) { fl =true;}
                graph.AddEdge(new Edge<int>(p[i - 1], p[j - 1]));
                num_arcs++;
                while (j != i)
                {
                    int k = rnd.Next(j + 1, i + 1);
                    if (k == j) { fl =true;}
                    graph.AddEdge(new Edge<int>(p[j - 1], p[k - 1]));
                    num_arcs++;
                    j = k;
                }
            }

            if (num_arcs < m)
            {
                for (int d = 0; d < m - num_arcs; d++)
                {
                    i = rnd.Next(1, n);
                    j = rnd.Next(i + 1, n + 1);
                    if (i == j) { fl = true; }
                    graph.AddEdge(new Edge<int>(p[i - 1], p[j - 1]));
                }
            }
            return graph;
        }
    }
}
