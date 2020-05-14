package com.eq.house;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	String title;
	Topic topic;
	Language lang;

	Quiz(String _title, Integer _topic, Integer _lang) {
		this.title = _title;

		switch (_topic) {
		case 0:
			this.topic = Topic.Sports;
			break;
		case 1:
			this.topic = Topic.GamesAndLeisure;
			break;
		case 2:
			this.topic = Topic.History;
			break;
		case 3:
			this.topic = Topic.Culture;
			break;
		case 4:
			this.topic = Topic.Literature;
			break;
		case 5:
			this.topic = Topic.Music;
			break;
		case 6:
			this.topic = Topic.FoodAndDrinks;
			break;
		case 7:
			this.topic = Topic.AnimalsAndNature;
			break;
		case 8:
			this.topic = Topic.Entertainment;
			break;
		case 9:
			this.topic = Topic.Language;
			break;
		case 10:
			this.topic = Topic.MoviesAndTVShows;
			break;
		case 11:
			this.topic = Topic.Geography;
			break;
		case 12:
			this.topic = Topic.Society;
			break;
		case 13:
			this.topic = Topic.TechnologyAndScience;
			break;
		case 14:
			this.topic = Topic.Other;
			break;
		default:
			System.err.println("warning: unknown topic");
			this.topic = Topic.Other;
			break;
		}

		switch (_lang) {
		case 0:
			this.lang = Language.English;
			break;
		case 1:
			this.lang = Language.Swedish;
			break;
		default:
			/*
			 * fallback...
			 */
			System.err.println("warning: unknown language (using fallback)");
			this.lang = Language.Swedish;
			break;
		}
	}
}
