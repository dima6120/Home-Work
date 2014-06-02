using System;
using System.Collections.Generic;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using FAS;
using QuickGraph;
using QuickGraph.Algorithms;
using QuickGraph.Data;


namespace FASSETests
{
    [TestClass]
    public class UnitTest1
    {
        GraphGenerator gg;
        public UnitTest1()
        {
            gg = new GraphGenerator();
        }
        [TestMethod]
        public void TestMethod1()
        {
            FASSE fas = new FASSE(); 
            BidirectionalGraph<int, Edge<int>> graph = gg.generate(20, 8, 60);  
            List<Edge<int>> mfas = fas.mfas(graph);

            foreach (Edge<int> e in mfas)
            {
                graph.RemoveEdge(e);
            }
            bool f = graph.IsDirectedAcyclicGraph<int, Edge<int>>();
            Assert.AreEqual(f, true);
        }
        [TestMethod]
        public void TestMethod2()
        {
            FASSE fas = new FASSE();
            BidirectionalGraph<int, Edge<int>> graph = gg.generate(60, 10, 100);
            List<Edge<int>> mfas = fas.mfas(graph);

            foreach (Edge<int> e in mfas)
            {
                graph.RemoveEdge(e);
            }
            bool f = graph.IsDirectedAcyclicGraph<int, Edge<int>>();
            Assert.AreEqual(f, true);
        }

        [TestMethod]
        public void TestMethod3()
        {
            FASSE fas = new FASSE();
            BidirectionalGraph<int, Edge<int>> graph = gg.generate(100, 14, 500);
            List<Edge<int>> mfas = fas.mfas(graph);

            foreach (Edge<int> e in mfas)
            {
                graph.RemoveEdge(e);
            }
            bool f = graph.IsDirectedAcyclicGraph<int, Edge<int>>();
            Assert.IsTrue(f);
        }

        [TestMethod]
        public void TestMethod4()
        {
            FASSE fas = new FASSE();
            BidirectionalGraph<int, Edge<int>> graph = gg.generate(200, 20, 1000);
            List<Edge<int>> mfas = fas.mfas(graph);

            foreach (Edge<int> e in mfas)
            {
                graph.RemoveEdge(e);
            }
            bool f = graph.IsDirectedAcyclicGraph<int, Edge<int>>();
            Assert.AreEqual(f, true);
        }
    }
}
