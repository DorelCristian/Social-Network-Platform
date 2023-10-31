package org.example.domain;

import java.util.*;

public class GrafRetele {
    private int numarNoduri;
    private Map<Integer, List<Integer>>graf ;
    private Map<Integer, List<Integer>> noduriInComponente = new HashMap<>(); // Map pentru nodurile din fiecare componentă conexă


    public GrafRetele(int numarNoduri) {
        this.numarNoduri = numarNoduri;
        graf= new HashMap<>();
    }

    public int getNumarNoduri() {
        return numarNoduri;
    }
    public void adaugaMuchie(int nod1,int nod2)
    {
        graf.computeIfAbsent(nod1, k -> new ArrayList<>()).add(nod2);
        graf.computeIfAbsent(nod2, k -> new ArrayList<>()).add(nod1);
    }
    public List<Integer> getVecini(int nod)
    {
        return graf.get(nod);
    }
    public int numarComponenteConexe()
    {
        int numarComponente=0;
        Set<Integer>noduriVizitate=new HashSet<>();
        for(int nod=1;nod<numarNoduri;nod++)
        {
            if(!noduriVizitate.contains(nod))
            {
                numarComponente++;
                bfs(nod,noduriVizitate);
            }
        }
        return numarComponente;
    }
    public void bfs(int nod,Set<Integer>noduriVizitate)
    {
        Queue<Integer>coada=new LinkedList<>();
        coada.add(nod);
        //noduriVizitate.add(nod);
        while(!coada.isEmpty())
        {
            int nodCurent=coada.poll();
            noduriVizitate.add(nodCurent);
            for(int vecin:graf.getOrDefault(nodCurent,new ArrayList<>()))
            {
                if(!noduriVizitate.contains(vecin))
                {
                    coada.add(vecin);
                }
            }
        }
    }
    /*public Map<Integer,Integer> numaraNoduriInComponente()
    {
        Map<Integer,Integer>noduriInComponente=new HashMap<>();
        Set<Integer>noduriVizitate=new HashSet<>();
        for (int nod = 1; nod <= numarNoduri; nod++) {
            if (!noduriVizitate.contains(nod)) {
                int numarComponenteConexe = 0;
                Queue<Integer> coada = new LinkedList<>();
                coada.add(nod);

                while (!coada.isEmpty()) {
                    int nodCurent = coada.poll();
                    noduriVizitate.add(nodCurent);
                    numarComponenteConexe++;

                    for (int vecin : graf.getOrDefault(nodCurent, new ArrayList<>())) {
                        if (!noduriVizitate.contains(vecin)) {
                            coada.add(vecin);
                        }
                    }
                }

                noduriInComponente.put(numarComponenteConexe, noduriInComponente.getOrDefault(numarComponenteConexe, 0) + 1);
            }
        }
        return noduriInComponente;
    }
    public int gasesteComponentaCuCeleMaiMulteNoduri() {
        Map<Integer, Integer> noduriInComponente = numaraNoduriInComponente();
        int maxNumarNoduri = 0;
        int componentaCuCeleMaiMulteNoduri = -1;

        for (Map.Entry<Integer, Integer> entry : noduriInComponente.entrySet()) {
            if (entry.getValue() > maxNumarNoduri) {
                maxNumarNoduri = entry.getValue();
                componentaCuCeleMaiMulteNoduri = entry.getKey();
            }
        }

        return componentaCuCeleMaiMulteNoduri;
    }
    public void exploreazaComponente()
    {
        Map<Integer, Integer> componente = numaraNoduriInComponente();
        Set<Integer> noduriVizitate = new HashSet();
        for (int nod = 1; nod <= numarNoduri; nod++) {
            if (!noduriVizitate.contains(nod)) {
                int numarComponenteConexe = 0;
                Queue<Integer> coada = new LinkedList<>();
                coada.add(nod);

                while (!coada.isEmpty()) {
                    int nodCurent = coada.poll();
                    noduriVizitate.add(nodCurent);
                    numarComponenteConexe++;
                    noduriInComponente.computeIfAbsent(numarComponenteConexe, k -> new ArrayList<>()).add(nodCurent);

                    for (int vecin : graf.getOrDefault(nodCurent, new ArrayList<>())) {
                        if (!noduriVizitate.contains(vecin)) {
                            coada.add(vecin);
                        }
                    }
                }
            }
        }
    }
    public List<Integer> noduriInComponenta(int componenta)
    {
        return noduriInComponente.get(componenta);
    }*/


}