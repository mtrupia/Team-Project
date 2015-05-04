package Team_Pro.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Filter {
	public static Map<String,String> iniFilter(){
	Map<String,String> Filter = new HashMap<String,String>();
	Filter.put("ass", "booty");
	Filter.put("asshole", "jerk");
	Filter.put("ass hole", "jerk");
	Filter.put("bastard","illegitimate child");
	Filter.put("bitch", "lovely person");
	Filter.put("bitches", "lovely people");
	Filter.put("hate", "strongly dislike");
	Filter.put("fuck", "fornicate");
	Filter.put("cunt", "****");
	Filter.put("fucking", "freaking");
	Filter.put("fucked", "had intercourse");
	Filter.put("boner","happy feeling");
	Filter.put("blowjob", "pleasure");
	Filter.put("bullshit", "rubbish");
	Filter.put("clit", "emotions");
	Filter.put("cock", "rooster");
	Filter.put("cum", "smile");
	Filter.put("dick", "unpleasant");
	Filter.put("dicks", "unpleasantries");
	Filter.put("dildo", "toy");
	Filter.put("dumbass", "blockhead");
	Filter.put("dumb ass", "block head");
	Filter.put("dyke","****");
	Filter.put("dike","****");
	Filter.put("douche", "nincompoop");
	Filter.put("fag", "***");
	Filter.put("fatass", "plump");
	Filter.put("handjob", "happy feeling");
	Filter.put("hand job", "happy feeling");
	Filter.put("hoe", "friend");
	Filter.put("ho", "friend");
	Filter.put("homo", "****");
	Filter.put("jackass", "nincompoop");
	Filter.put("jack ass", "nincompoop");
	Filter.put("jizz", "liquid");
	Filter.put("lesbo", "****");
	Filter.put("motherfucker", "mother father");
	Filter.put("mother fucker", "mother father");
	Filter.put("nigga", "African American");
	Filter.put("nigger", "African American");
	Filter.put("niggers", "African Americans");
	Filter.put("penis", "*****");
	Filter.put("piss", "pee");
	Filter.put("pissed", "mad");
	Filter.put("pussy", "*****");
	Filter.put("queef", "poot");
	Filter.put("queer", "*****");
	Filter.put("shit", "poop");
	Filter.put("shitty", "bad");
	Filter.put("slut", "woman");
	Filter.put("twat", "nitwit");
	Filter.put("twats", "nitwits");
	Filter.put("vag", "***");
	Filter.put("vagina", "******");
	Filter.put("whore", "friend");
	return Filter;
	}
	
	private static Map<String,String> filter = iniFilter();
	
	public static String FilterComment(String text){
		String arr = text + " "; //space added to detect curse at end of comment
		Iterator<Entry<String, String>> it = filter.entrySet().iterator();
		while (it.hasNext()){
			Map.Entry<String, String> pair = (Map.Entry<String, String>) it.next();
			if (text.split(" ").length == 1) {
				if (text.toLowerCase().contains(pair.getKey())) {
					arr = text.toLowerCase().replaceAll(pair.getKey(), pair.getValue());
				}
			}
			if (text.toLowerCase().contains(pair.getKey() + " ") || text.toLowerCase().contains(pair.getKey() + "!") || text.toLowerCase().contains(pair.getKey() + "?") || text.toLowerCase().contains(pair.getKey() + ".") || text.toLowerCase().contains(pair.getKey() + ",")) {
				arr = text.toLowerCase().replaceAll(pair.getKey(), pair.getValue());
			}
		}
		 return arr;
	}
}
