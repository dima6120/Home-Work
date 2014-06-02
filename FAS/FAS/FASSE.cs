using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using QuickGraph;
using QuickGraph.Algorithms;
using QuickGraph.Data;
using System.Collections;

namespace FAS
{
    public class FASSE
    {
        int iV;

        public List<Edge<int>> mfas(BidirectionalGraph<int, Edge<int>> G)
        {
            List<Edge<int>> F = new List<Edge<int>>();
            IDictionary<int, int> P = new Dictionary<int, int>();
            
            int sccCount = G.StronglyConnectedComponents(out P);

            if (sccCount == 0)
            {
                return F;
            }

            if (sccCount == 1)
            {
                Tuple<List<int>, List<int>> T = bisect(G);
                F = mfas(subGraph(T.Item1, G));
                F.AddRange(mfas(subGraph(T.Item2, G)));
                F.AddRange(fromV2toV1(T, G));
            }
            else
            {
                var scc = new HashSet<int>(P.Values);
                foreach (int k in scc)
                {
                    List<int> S = P.Select(x => x).Where(x => x.Value == k).Select(x => x.Key).ToList<int>();
                    F.AddRange(mfas(subGraph(S, G)));
                }
            }

            return F;
        }

        BidirectionalGraph<int, Edge<int>> subGraph(List<int> V, BidirectionalGraph<int, Edge<int>> G)
        {
            return G.Edges.Select(x => x)
                .Where(x => V.Exists(t => t == x.Source) && V.Exists(t => t == x.Target)).ToBidirectionalGraph<int, Edge<int>>();
        }

        List<Edge<int>> fromV2toV1(Tuple<List<int>, List<int>> T, BidirectionalGraph<int, Edge<int>> G)
        {
           return G.Edges.Select(x => x)
               .Where(x => T.Item1.Exists(t => t == x.Target) && T.Item2.Exists(t => t == x.Source)).ToList<Edge<int>>();
        }

        List<Edge<int>> fromV2toV1i(Tuple<List<int>, List<int>> T, BidirectionalGraph<int, Edge<int>> G, int i)
        {
            return G.Edges.Select(x => x)
                .Where(x => (iV == 2 && i == x.Target || T.Item1.Exists(t => ((iV == 1 && t != i) || (iV == 2)) && t == x.Target))
                    && (iV == 1 && i == x.Source || T.Item2.Exists(t => ((iV == 2 && t != i) || (iV == 1)) && t == x.Source))).ToList<Edge<int>>();
        }

        int gain(int i, Tuple<List<int>, List<int>> V, BidirectionalGraph<int, Edge<int>> G)
        {
            iV = V.Item1.Exists(t => t == i) ? 1 : 2;

            return fromV2toV1(V, G).Count() - fromV2toV1i(V, G, i).Count();
        }

        void perturb(Tuple<List<int>, List<int>> V, int p, BidirectionalGraph<int, Edge<int>> G)
        {
            Stack<int> S1 = new Stack<int>();
            Stack<int> S2 = new Stack<int>();
            Random rand = new Random();

            foreach (int i in G.Vertices)
            {
                if (gain(i, V, G) > -rand.Next(Math.Abs(p) + 1))
                {
                    if (iV == 1)
                    {
                        V.Item1.Remove(i);
                        V.Item2.Add(i);
                        S2.Push(i);
                    }
                    else
                    {
                        V.Item2.Remove(i);
                        V.Item1.Add(i);
                        S1.Push(i);
                    }
                }
            }


            int j = V.Item1.Count() > V.Item2.Count() ? 1 : 2;
            double alpha = 0.6; //0.5 <= alpha <= 1
            int k;
            int vc = G.Vertices.Count<int>();

            while (j == 1 && V.Item1.Count() > alpha * vc && S1.Count() != 0 ||
                j == 2 && V.Item2.Count() > alpha * vc && S2.Count() != 0)
            {
                if (j == 1)
                {
                    k = S1.Pop();
                    V.Item1.Remove(k);
                    V.Item2.Add(k);
                }
                else
                {
                    k = S2.Pop();
                    V.Item2.Remove(k);
                    V.Item1.Add(k);
                }
            }
        }

        Tuple<List<int>, List<int>> bisect(BidirectionalGraph<int, Edge<int>> G)
        {
            List<int> vl = G.Vertices.ToList<int>();
            int vlc = vl.Count();
            Tuple<List<int>, List<int>> V = new Tuple<List<int>, List<int>>(vl.GetRange(0, vlc / 2), vl.GetRange(vlc / 2, vlc - vlc / 2));
            Tuple<List<int>, List<int>> B = new Tuple<List<int>, List<int>>(new List<int>(V.Item1), new List<int>(V.Item2));
            int p = -1;//p0 == -1
            int R = 1;
            int d = 2;
            int counter = 0;
            int Cpre, Cpost;
            int BC;
            Cpre = fromV2toV1(V, G).Count();
            BC = Cpre;
            do 
            {
                perturb(V, p, G);
                Cpost = fromV2toV1(V, G).Count();
                if (Cpost < Cpre && BC > Cpost) {
                    B = new Tuple<List<int>, List<int>> (new List<int>(V.Item1), new List<int>(V.Item2));
                    BC = Cpost;
                    counter -= R;
                } else {
                    counter++;
                }
                if (Cpost == Cpre) {
                    p = p - d;
                } else {
                    p = -1; //p0 == -1;
                }
                Cpre = Cpost;
            } while(!(counter > R)); 
            
            return B;
        }

    }
}
