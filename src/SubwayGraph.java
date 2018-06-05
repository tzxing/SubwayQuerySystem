import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class SubwayGraph
{
	public Map<String, Station> vertices;
	public Map<String, Station> lines;
	
	public SubwayGraph()
	{
		vertices=new HashMap<>();
		lines=new HashMap<>();
	}
	
	public SubwayGraph(Set<List<Station>> lineSet)
	{


		this();
		System.out.println(DataBuilder.line3.get(9).edgeList);
		//初始化vertices
		for(List<Station> line:lineSet)
		{
			this.Add(line);
		}
		for(Station station:vertices.values())
		{
			if(station.line.size()>1)
				station.isTransfer=true;
		}
		
		//初始化lines
		for(Station station:vertices.values())
		{
			for(String string:station.line)
			{
				if(!lines.containsKey(string))
				{
					lines.put(string, new Station(string,"-1"));
				}
			}

			if(station.isTransfer)
			{
				for(String string1:station.line)
				{
					for(String string2:station.line)
					{
						if(!lines.get(string1).edgeList.contains((String)string2))
							lines.get(string1).edgeList.add(string2);
					}
				}
			}
		}
		System.out.println(DataBuilder.line3.get(9).edgeList);
	}
	
	public void Add(List<Station> line)
	{
		System.out.println(DataBuilder.line3.get(9).edgeList);

		for(int i=0;i<line.size();i++)
		{
			Station station = null;
			try
			{
				station = line.get(i).clone();
			}
			catch (CloneNotSupportedException e)
			{
				e.printStackTrace();
			}
//			System.out.println(line.get(i).edgeList);
			if(!vertices.containsKey(station.label))
				vertices.put(station.label, station);
			else 
			{
				for(String s:station.edgeList)
					vertices.get(station.label).edgeList.add(s);
				for(String s:station.line)
					if(!vertices.get(station.label).line.contains(s))
					{
						vertices.get(station.label).line.add(s);
					}
			}
//			System.out.println(vertices.get(station.label).edgeList);
		}
//		for(int i=0;i<line.size();i++)
//		{
//			System.out.println(DataBuilder.line3.get(i).edgeList);
//		}
		System.out.println(DataBuilder.line3.get(9).edgeList);

	}
	
	public String toString()
	{
		return vertices.toString();
	}
	
	public static ReturnStruct shortestRoute(String s1,String s2,Map<String, Station> collection)
	{
		StringBuffer ret=new StringBuffer();
		String routeDisplay;
		Queue<Station> queue=new LinkedList<>();
		Stack<Station> route=new Stack<>();
		for (Map.Entry<String, Station> entry : collection.entrySet())
			entry.getValue().dist=-1;
		collection.get(s1).dist=0;
		queue.add(collection.get(s1));
		while(!queue.isEmpty())
		{
			Station vStation=queue.poll();
			for(String edgeStation:vStation.edgeList)
			{
				System.out.println(collection.get(edgeStation).dist);
				if(collection.get(edgeStation).dist==-1)
				{
					collection.get(edgeStation).dist=vStation.dist+1;
					collection.get(edgeStation).preStation=vStation.label;
					queue.add(collection.get(edgeStation));
				}
			}
		}
		
		for(Station station=collection.get(s2);!station.label.equals(s1);station=collection.get(station.preStation))
		{
			route.push(station);
		}
		//ret.append(this.printRoute(route));
		Station pre=null;
		while(!route.isEmpty())
		{
			Station station=route.pop();
			
			if(station.isTransfer&&pre!=null&&!route.isEmpty()&&!pre.onTheSameLine(route.peek()))
			{
				ret.append( ">"+station.label+"（换乘）");
			}
			else
				ret.append(">"+station.label);
			pre=station;
		}
		ret.insert(0, s1);
		routeDisplay=ret.toString();
		ret.insert(0, "线路为：");
		ret.insert(0, "从"+s1+"到"+s2+"的最短路径共计"+collection.get(s2).dist+"站"+"\n");
		return new ReturnStruct(ret.toString(), routeDisplay,collection.get(s2).dist);
	}
	
//	private String printRoute(Stack<Station> route)
//	{
//		StringBuffer ret=new StringBuffer();
//		Station pre=null;
//		while(!route.isEmpty())
//		{
//			Station station=route.pop();
//			
//			if(station.isTransfer&&pre!=null&&!route.isEmpty()&&!pre.onTheSameLine(route.peek()))
//			{
//				ret.append( ">"+station.label+"（换乘）");
//			}
//			else
//				ret.append(">"+station.label);
//			pre=station;
//		}
//		return ret.toString();
//	}
	
	public String leastTransferRoute(String s1,String s2)
	{
		int min=Integer.MAX_VALUE;
		String temp = null;
		StringBuffer ret=new StringBuffer();
		String[] start=new String[vertices.get(s1).line.size()];
		String[] destination=new String[vertices.get(s2).line.size()];
		vertices.get(s1).line.toArray(start);
		vertices.get(s2).line.toArray(destination);
		for(String a1:start)
		{
			for(String a2:destination)
			{
				if(SubwayGraph.shortestRoute(a1,a2,this.lines).num<min)
				{
					min=SubwayGraph.shortestRoute(a1,a2,this.lines).num;
					temp=SubwayGraph.shortestRoute(a1,a2,this.lines).route;
				}
			}
		}
		ret.append(temp);
		ret.insert(0, "的最少换乘线路为：");
		ret.insert(0, "从"+s1+"到"+s2);
		return ret.toString();
	}
	
	public Map<String, Station> listToMap(List<Station> list)
	{
		HashMap<String, Station> ret=new HashMap<>();
		for(Station x:list)
			ret.put(x.label, x);
		return ret;
	}
	
	
	public String routeOntheSamneLine(String a,String b,Map<String, Station> map)//==================================
	{
		return SubwayGraph.shortestRoute(a, b, map).route;//===================
	}
	
	public static void main(String[] args)
	{
		System.out.println(DataBuilder.line3.get(9).edgeList);
		SubwayGraph subwayGraph=new SubwayGraph(DataBuilder.lineSet);
		System.out.println(DataBuilder.line3.get(9).edgeList);
		//System.out.println(subwayGraph.vertices.get("南京南站").line);
		//System.out.println(subwayGraph.vertices.get("禄口机场站").isTransfer);
		//System.out.println(SubwayGraph.shortestRoute("浦口万汇城站", "龙眠大道站",subwayGraph.vertices));
		//System.out.println(subwayGraph.leastTransferRoute("浦口万汇城站", "龙眠大道站"));
		//System.out.println(subwayGraph.vertices.get("3号线").edgeList.toString());
		//Map<String, Station> line11=subwayGraph.listToMap(DataBuilder.line3);
//		System.out.println(DataBuilder.line3);
//		System.out.println(line11);
		System.out.println(subwayGraph.routeOntheSamneLine("南京站", "南京南站",subwayGraph.listToMap(DataBuilder.line3)));
	}
	
}
