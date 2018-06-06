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
		vertices = new HashMap<>();
		lines = new HashMap<>();
	}

	public SubwayGraph(Set<List<Station>> lineSet)
	{
		this();
		// 初始化vertices
		for (List<Station> line : lineSet)
		{
			this.Add(line);
		}
		for (Station station : vertices.values())
		{
			if (station.line.size() > 1)
				station.isTransfer = true;
		}

		// 初始化lines
		for (Station station : vertices.values())
		{
			for (String string : station.line)
			{
				if (!lines.containsKey(string))
				{
					lines.put(string, new Station(string, "-1"));
				}
			}
			if (station.isTransfer)
			{
				for (String string1 : station.line)
				{
					for (String string2 : station.line)
					{
						if (!lines.get(string1).edgeList.contains((String) string2))
							lines.get(string1).edgeList.add(string2);
					}
				}
			}
		}
	}

	public void Add(List<Station> line)
	{
		for (int i = 0; i < line.size(); i++)
		{
			Station station = null;
			try
			{
				station = line.get(i).clone();
			}
			catch (CloneNotSupportedException e)
			{

			}
			if (!vertices.containsKey(station.label))
				vertices.put(station.label, station);
			else
			{
				for (String s : station.edgeList)
					vertices.get(station.label).edgeList.add(s);
				for (String s : station.line)
					if (!vertices.get(station.label).line.contains(s))
						vertices.get(station.label).line.add(s);
			}
		}
	}

	public String toString()
	{
		return vertices.toString();
	}

	public static ReturnStruct shortestRouteWithnotFirstName(String s1, String s2, Map<String, Station> collection)
	{
		// StringBuffer ret=new StringBuffer();
		StringBuffer routeDisplay = new StringBuffer();
		Queue<Station> queue = new LinkedList<>();
		Stack<Station> route = new Stack<>();
		for (Map.Entry<String, Station> entry : collection.entrySet())
			entry.getValue().dist = -1;
		collection.get(s1).dist = 0;
		queue.add(collection.get(s1));
		while (!queue.isEmpty())
		{
			Station vStation = queue.poll();
			for (String edgeStation : vStation.edgeList)
			{
				if (collection.get(edgeStation).dist == -1)
				{
					collection.get(edgeStation).dist = vStation.dist + 1;
					collection.get(edgeStation).preStation = vStation.label;
					queue.add(collection.get(edgeStation));
				}
			}
		}

		for (Station station = collection.get(s2); !station.label.equals(s1); station = collection
				.get(station.preStation))
		{
			route.push(station);
		}
		Station pre = null;
		while (!route.isEmpty())
		{
			Station station = route.pop();

			if (station.isTransfer && pre != null && !route.isEmpty() && !pre.onTheSameLine(route.peek()))
			{
				routeDisplay.append(">" + station.label + "（换乘）");
			}
			else
				routeDisplay.append(">" + station.label);
			pre = station;
		}
		return new ReturnStruct(routeDisplay.toString(), collection.get(s2).dist);
	}

	public String shortestTakeRoute(String s1, String s2)
	{
		StringBuffer ret = new StringBuffer();
		ret.append(SubwayGraph.shortestRoute(s1, s2, vertices).route);
		ret.insert(0, "线路为：");
		ret.insert(0, "从" + s1 + "到" + s2 + "的最短路径共计" + SubwayGraph.shortestRoute(s1, s2, vertices).num + "站" + "\n");
		return ret.toString();
	}

	public static ReturnStruct shortestRoute(String s1, String s2, Map<String, Station> collection)
	{

		StringBuffer routeDisplay = new StringBuffer();
		Queue<Station> queue = new LinkedList<>();
		Stack<Station> route = new Stack<>();
		for (Map.Entry<String, Station> entry : collection.entrySet())
			entry.getValue().dist = -1;
		collection.get(s1).dist = 0;
		queue.add(collection.get(s1));
		while (!queue.isEmpty())
		{
			Station vStation = queue.poll();
			for (String edgeStation : vStation.edgeList)
			{
				if (collection.get(edgeStation).dist == -1)
				{
					collection.get(edgeStation).dist = vStation.dist + 1;
					collection.get(edgeStation).preStation = vStation.label;
					queue.add(collection.get(edgeStation));
				}
			}
		}

		for (Station station = collection.get(s2); !station.label.equals(s1); station = collection.get(station.preStation))
		{
			route.push(station);
		}
		Station pre = null;
		while (!route.isEmpty())
		{
			Station station = route.pop();

			if (station.isTransfer && pre != null && !route.isEmpty() && !pre.onTheSameLine(route.peek()))
			{
				routeDisplay.append(">" + station.label + "（换乘）");
			}
			else
				routeDisplay.append(">" + station.label);
			pre = station;
		}
		routeDisplay.insert(0, s1);
		return new ReturnStruct(routeDisplay.toString(), collection.get(s2).dist);
	}

	public String leastTransferRoute(String s1, String s2)
	{
		int min = Integer.MAX_VALUE;
		int num = 0;
		String temp = null;
		StringBuffer ret = new StringBuffer();
		String[] start = new String[vertices.get(s1).line.size()];
		String[] destination = new String[vertices.get(s2).line.size()];
		String[] lineRoute;
		vertices.get(s1).line.toArray(start);
		vertices.get(s2).line.toArray(destination);
		for (String a1 : start)
		{
			for (String a2 : destination)
			{
				if (SubwayGraph.shortestRoute(a1, a2, this.lines).num < min)
				{
					min = SubwayGraph.shortestRoute(a1, a2, this.lines).num;
					temp = SubwayGraph.shortestRoute(a1, a2, this.lines).route;
				}
			}
		}
		lineRoute = temp.split(">");
		if(lineRoute.length==1)
		{
			ret.append(SubwayGraph.routeOntheSamneLine(s1, s2, lineRoute[0]));
			num=SubwayGraph.routeOntheSamneLine(s1, s2, lineRoute[0]).num;
		}
		else
		{
			String pre, next = null;
			pre = s1;
			for (int i = 0; i < lineRoute.length - 1; i++)
			{
				String probablyTransfer[] = this.getTransferStationName(lineRoute[i], lineRoute[i + 1]).split(" ");
				int min_1 = Integer.MAX_VALUE;
				for (int j = 0; j < probablyTransfer.length; j++)
				{
					next = probablyTransfer[j];
					if (SubwayGraph.routeOntheSamneLine(pre, next, lineRoute[i]).num < min_1)
					{
						if (i == 0)
						{
							min_1 = SubwayGraph.routeOntheSamneLine(pre, next, lineRoute[i]).num;
							temp = SubwayGraph.routeOntheSamneLine(pre, next, lineRoute[i]).route;
						}
						else
						{
							min_1 = SubwayGraph.routeOntheSamneLineWithnotFirstName(pre, next, lineRoute[i]).num;
							temp = SubwayGraph.routeOntheSamneLineWithnotFirstName(pre, next, lineRoute[i]).route;
						}
					}
				}
				ret.append(temp);
				num += min_1;
				ret.append("（换乘）");
				pre = next;
			}
			ret.append(SubwayGraph.routeOntheSamneLineWithnotFirstName(next, s2, lineRoute[lineRoute.length - 1]).route);
			num += SubwayGraph.routeOntheSamneLine(next, s2, lineRoute[lineRoute.length - 1]).num;
		}
		
		
		
		
		
		
		
		
		
		
		ret.insert(0, "线路为：");
		ret.insert(0, "从" + s1 + "到" + s2 + "的最少换乘线路共计：" + num + "站" + "\n");

		return ret.toString();
	}

	public static Map<String, Station> listToMap(List<Station> list)
	{
		HashMap<String, Station> ret = new HashMap<>();
		for (Station x : list)
			ret.put(x.label, x);
		return ret;
	}

	public static ReturnStruct routeOntheSamneLineWithnotFirstName(String a, String b, String line)
	{
		Map<String, Station> singleLine = new HashMap<>();
		switch (line)
		{
			case "1号线":
				singleLine = SubwayGraph.listToMap(DataBuilder.line1);
				break;
			case "2号线":
				singleLine = SubwayGraph.listToMap(DataBuilder.line2);
				break;
			case "3号线":
				singleLine = SubwayGraph.listToMap(DataBuilder.line3);
				break;
			case "4号线":
				singleLine = SubwayGraph.listToMap(DataBuilder.line4);
				break;
			case "10号线":
				singleLine = SubwayGraph.listToMap(DataBuilder.line10);
				break;
			case "S1号线":
				singleLine = SubwayGraph.listToMap(DataBuilder.lineS1);
				break;
			case "S3号线":
				singleLine = SubwayGraph.listToMap(DataBuilder.lineS3);
				break;
			case "S7号线":
				singleLine = SubwayGraph.listToMap(DataBuilder.lineS7);
				break;
			case "S8号线":
				singleLine = SubwayGraph.listToMap(DataBuilder.lineS8);
				break;
			case "S9号线":
				singleLine = SubwayGraph.listToMap(DataBuilder.lineS9);
				break;
			default:
				break;
		}
		return SubwayGraph.shortestRouteWithnotFirstName(a, b, singleLine);
	}

	public static ReturnStruct routeOntheSamneLine(String a, String b, String line)
	{
		Map<String, Station> singleLine = new HashMap<>();
		switch (line)
		{
			case "1号线":
				singleLine = SubwayGraph.listToMap(DataBuilder.line1);
				break;
			case "2号线":
				singleLine = SubwayGraph.listToMap(DataBuilder.line2);
				break;
			case "3号线":
				singleLine = SubwayGraph.listToMap(DataBuilder.line3);
				break;
			case "4号线":
				singleLine = SubwayGraph.listToMap(DataBuilder.line4);
				break;
			case "10号线":
				singleLine = SubwayGraph.listToMap(DataBuilder.line10);
				break;
			case "S1号线":
				singleLine = SubwayGraph.listToMap(DataBuilder.lineS1);
				break;
			case "S3号线":
				singleLine = SubwayGraph.listToMap(DataBuilder.lineS3);
				break;
			case "S7号线":
				singleLine = SubwayGraph.listToMap(DataBuilder.lineS7);
				break;
			case "S8号线":
				singleLine = SubwayGraph.listToMap(DataBuilder.lineS8);
				break;
			case "S9号线":
				singleLine = SubwayGraph.listToMap(DataBuilder.lineS9);
				break;
			default:
				break;
		}
		return SubwayGraph.shortestRoute(a, b, singleLine);
	}

	public String getTransferStationName(String s1, String s2)
	{
		StringBuffer ret = new StringBuffer();
		for (Station station : vertices.values())
		{
			if (station.line.contains(s1) && station.line.contains(s2))
				ret.append(station.label + " ");
		}
		return ret.toString();
	}

	public static void main(String[] args)
	{
		SubwayGraph subwayGraph=new SubwayGraph(DataBuilder.lineSet);
		System.out.println(subwayGraph.shortestTakeRoute("南京站", "龙眠大道站"));
		System.out.println(subwayGraph.leastTransferRoute("南京站", "龙眠大道站"));
		System.out.println(subwayGraph.shortestTakeRoute("雨山路站", "无想山站"));
		System.out.println(subwayGraph.leastTransferRoute("雨山路站", "无想山站"));
	}

}
