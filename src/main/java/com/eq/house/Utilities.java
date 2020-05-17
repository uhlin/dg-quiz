package com.eq.house;

public class Utilities {
	public static Topic intToTopic(Integer i) {
		switch (i) {
		case 0:
			return Topic.Sports;
		case 1:
			return Topic.GamesAndLeisure;
		case 2:
			return Topic.History;
		case 3:
			return Topic.Culture;
		case 4:
			return Topic.Literature;
		case 5:
			return Topic.Music;
		case 6:
			return Topic.FoodAndDrinks;
		case 7:
			return Topic.AnimalsAndNature;
		case 8:
			return Topic.Entertainment;
		case 9:
			return Topic.Language;
		case 10:
			return Topic.MoviesAndTVShows;
		case 11:
			return Topic.Geography;
		case 12:
			return Topic.Society;
		case 13:
			return Topic.TechnologyAndScience;
		case 14:
			return Topic.Other;
		}

		System.err.println("intToTopic: warning: unknown topic");
		return Topic.Other;
	} /* intToTopic() */

	public static String topicToString(Topic topic) {
		switch (topic) {
		case Sports:
			return "Sports";
		case GamesAndLeisure:
			return "Games & Leisure";
		case History:
			return "History";
		case Culture:
			return "Culture";
		case Literature:
			return "Literature";
		case Music:
			return "Music";
		case FoodAndDrinks:
			return "Food & Drinks";
		case AnimalsAndNature:
			return "Animals & Nature";
		case Entertainment:
			return "Entertainment";
		case Language:
			return "Language";
		case MoviesAndTVShows:
			return "Movies & TV Shows";
		case Geography:
			return "Geography";
		case Society:
			return "Society";
		case TechnologyAndScience:
			return "Technology & Science";
		case Other:
			return "Other";
		}

		System.err.println("topicToString: warning: unknown topic");
		return "Other";
	} /* topicToString() */

	public static Language intToLanguage(Integer i) {
		switch (i) {
		case 0:
			return Language.English;
		case 1:
			return Language.Swedish;
		}

		System.err.println("intToLanguage: warning: unknown language (using fallback)");
		return Language.Swedish;
	} /* intToLanguage() */

	public static String languageToString(Language lang) {
		switch (lang) {
		case English:
			return "English";
		case Swedish:
			return "Swedish";
		}

		System.err.println("languageToString: warning: unknown language");
		return "Unspecified";
	} /* languageToString() */
}
