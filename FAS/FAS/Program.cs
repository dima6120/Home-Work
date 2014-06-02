using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using QuickGraph;
using QuickGraph.Algorithms;
using QuickGraph.Data;
using QuickGraph.Graphviz;
using QuickGraph.Graphviz.Dot;

namespace FAS
{
    public sealed class FileDotEngine : IDotEngine
    {
        public string Run(GraphvizImageType imageType, string dot, string outputFileName)
        {
            string output = outputFileName + ".dot";
            File.WriteAllText(output, dot);
            return output;
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            FASSE fas = new FASSE();
            var edges = new Edge<int>[] { new Edge<int>(0, 1)
                , new Edge<int>(0, 6)
                , new Edge<int>(1, 3)
                , new Edge<int>(2, 0)
                , new Edge<int>(2, 1)
                , new Edge<int>(3, 2)
                , new Edge<int>(3, 4)
                , new Edge<int>(4, 5)
                , new Edge<int>(5, 0)
                , new Edge<int>(5, 6)
                , new Edge<int>(6, 4)
                , new Edge<int>(6, 3)
            };

            GraphGenerator gg = new GraphGenerator();


            BidirectionalGraph<int, Edge<int>> graph = gg.generate(20, 10, 100);//edges.ToBidirectionalGraph<int, Edge<int>>();
            GraphvizAlgorithm<int, Edge<int>> graphviz = new GraphvizAlgorithm<int, Edge<int>>(graph);
            graphviz.Generate(new FileDotEngine(), "g_b");
            List<Edge<int>> mfas = fas.mfas(graph);
            
            foreach (Edge<int> e in mfas)
            {
                graph.RemoveEdge(e);
            }
            bool f = graph.IsDirectedAcyclicGraph<int, Edge<int>>();
            graphviz.Generate(new FileDotEngine(), "g_a");
            Console.WriteLine(mfas.Count());
        }
    }
}
