import { Component, OnInit, Input } from '@angular/core';
import { RatingChangeEvent } from 'angular-star-rating';


interface Image {
  src: string;
  text: string;
}
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  images: Array<Image>;

  skills: string[];
  numStars: number;
  onRatingChangeResult: RatingChangeEvent;
 
  constructor() { }

  ngOnInit() {
    this.images = [
      { src: 'assets/images/training7.jpg', text: 'home.TrainingSkills' },
      { src: 'assets/images/training5.jpg', text: 'home.AchieveGoals' },
      { src: 'assets/images/training6.jpg', text: 'home.GainExperience' },
      { src: 'assets/images/training4.jpg', text: 'home.ImproveKnowledge' }
    ];

    this.skills = ['Html', 'Saas', 'Java script', 'Java8', 'Relational database'];
    this.numStars = 3;
  }

  onRatingChange = ($event: RatingChangeEvent) => {
    console.log('onRatingUpdated $event: ', $event);
    this.numStars = $event.rating;
    this.onRatingChangeResult = $event;
  };
}
