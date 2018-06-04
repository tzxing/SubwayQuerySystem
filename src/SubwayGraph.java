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
	
	public SubwayGraph()
	{
		vertices=new HashMap<>();
	}
//	public SubwayGraph(List<Station> line)
//	{
//		this();
//		for(int i=0;i<line.size();i++)
//			vertices.put(line.get(i).label, line.get(i));
//	}
	public SubwayGraph(Set<List<Station>> lineSet)
	{
		this();
		for(List<Station> line:lineSet)
		{
			this.Add(line);
		}
		for(Station station:vertices.values())
		{
			if(station.line.size()>1)
				station.isTransfer=true;
		}
	}
	public void Add(List<Station> line)
	{
		for(int i=0;i<line.size();i++)
		{
			Station station=line.get(i);
			if(!vertices.containsKey(station.label))
				vertices.put(station.label, station);
			else 
			{
				for(String s:station.edgeList)
					vertices.get(station.label).edgeList.add(s);
				for(String s:station.line)
					vertices.get(station.label).line.add(s);
			}
		}
	}
	public String toString()
	{
		return vertices.toString();
	}
	
	public String shortestRoute(String s1,String s2)
	{
		StringBuffer ret=new StringBuffer();
		Queue<Station> queue=new LinkedList<>();
		Stack<Station> route=new Stack<>();
		for (Map.Entry<String, Station> entry : vertices.entrySet())
			entry.getValue().dist=-1;
		vertices.get(s1).dist=0;
		queue.add(vertices.get(s1));
		while(!queue.isEmpty())
		{
			Station vStation=queue.poll();
			for(String edgeStation:vStation.edgeList)
				if(vertices.get(edgeStation).dist==-1)
				{
					vertices.get(edgeStation).dist=vStation.dist+1;
					vertices.get(edgeStation).preStation=vStation.label;
					queue.add(vertices.get(edgeStation));
				}
		}
		
		for(Station station=vertices.get(s2);!station.preStation.equals(s1);station=vertices.get(station.preStation))
		{
			route.push(station);
		}
		
		ret.append(this.printRoute(route));
		ret.insert(0, s1);
		ret.insert(0, "线路为：");
		ret.insert(0, "从"+s1+"到"+s2+"共计"+vertices.get(s2).dist+"站"+"\n");
		return ret.toString();
	}
	
	private String printRoute(Stack<Station> route)
	{
		StringBuffer ret=new StringBuffer();
		Station pre=null;
		while(!route.isEmpty())
		{
			boolean needTransfer=true;
			Station station=route.pop();
			if(!station.isTransfer)
				ret.append(">"+station.label);
			else if(pre!=null)
			{
				for(String a:pre.line)
				{
					for(String b:route.peek().line)
						if(a.equals(b))
							needTransfer=false;
				}
			}
			if(needTransfer&&station.isTransfer)
				ret.append( ">"+station.label+"（换乘）");
			pre=station;
		}
		return ret.toString();
	}
	
	
	
	public static void main(String[] args)
	{
		SubwayGraph subwayGraph=new SubwayGraph(DataBuilder.lineSet);
		//System.out.println(subwayGraph);
		System.out.println(subwayGraph.vertices.get("南京南站").line);
		System.out.println(subwayGraph.vertices.get("龙眠大道站").isTransfer);
		System.out.println(subwayGraph.shortestRoute("南京站", "龙眠大道站"));
	}
	
}
