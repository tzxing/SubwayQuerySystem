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
		for(List<Station> line:lineSet)
		{
			this.Add(line);
		}
		for(Station station:vertices.values())
		{
			if(station.line.size()>1)
				station.isTransfer=true;
		}
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
		//vertices.putAll(lines);
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
					if(!vertices.get(station.label).line.contains(s))
					{
						vertices.get(station.label).line.add(s);
					}
			}
		}
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
				if(collection.get(edgeStation).dist==-1)
				{
					collection.get(edgeStation).dist=vStation.dist+1;
					collection.get(edgeStation).preStation=vStation.label;
					queue.add(collection.get(edgeStation));
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
				ret.append( ">"+station.label+"�����ˣ�");
			}
			else
				ret.append(">"+station.label);
			pre=station;
		}
		ret.insert(0, s1);
		routeDisplay=ret.toString();
		ret.insert(0, "��·Ϊ��");
		ret.insert(0, "��"+s1+"��"+s2+"�����·������"+collection.get(s2).dist+"վ"+"\n");
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
//				ret.append( ">"+station.label+"�����ˣ�");
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
		ret.insert(0, "�����ٻ�����·Ϊ��");
		ret.insert(0, "��"+s1+"��"+s2);
		return ret.toString();
	}
	
	public String routeOntheSamneLine(Station a,Station b,List<Station> line)//==================================
	{
		Map<String, Station> temp=new HashMap<>();
		if(!a.line.contains(line)&&b.line.contains(line))
			return null;
		for(int i=0;i<line.size();i++)
		{
			Station station=line.get(i);
			if(!temp.containsKey(station.label))
				temp.put(station.label, station);
			else 
			{
				for(String s:station.edgeList)
					temp.get(station.label).edgeList.add(s);
				for(String s:station.line)
					if(!temp.get(station.label).line.contains(s))
					{
						temp.get(station.label).line.add(s);
					}
			}
		}
		for(Station s:temp.values())
		{
			s.isTransfer=vertices.get(s.label).isTransfer;
		}
		
		return SubwayGraph.shortestRoute(a.label, b.label, temp).route;//===================
	}
	
	public static void main(String[] args)
	{
		SubwayGraph subwayGraph=new SubwayGraph(DataBuilder.lineSet);
		//System.out.println(subwayGraph.vertices.get("�Ͼ���վ").line);
		//System.out.println(subwayGraph.vertices.get("»�ڻ���վ").isTransfer);
		//System.out.println(SubwayGraph.shortestRoute("�ֿ�����վ", "���ߴ��վ",subwayGraph.vertices));
		//System.out.println(subwayGraph.leastTransferRoute("�ֿ�����վ", "���ߴ��վ"));
		//System.out.println(subwayGraph.vertices.get("3����").edgeList.toString());
		Station s1=subwayGraph.vertices.get("�Ͼ�վ");
		Station s2=subwayGraph.vertices.get("���ߴ��վ");
		String string=subwayGraph.routeOntheSamneLine(s1, s2, DataBuilder.line1);
		System.out.println(string);
	}
	
}
