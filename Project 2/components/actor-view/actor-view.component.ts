import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Actor } from 'src/app/models/actor';
import { Tabulator } from 'tabulator-tables/dist/js/tabulator.min.js';
import { ActorApiService } from 'src/app/services/actor-api.service';
import { OMDBAPI } from 'src/app/models/OMDBAPI';
import { MovieAPIService } from 'src/app/services/movie-api.service';

@Component({
  selector: 'app-actor-view',
  templateUrl: './actor-view.component.html',
  styleUrls: ['./actor-view.component.css']
})
export class ActorViewComponent implements OnInit {
  private id: string;
  public firstName: string;
  public lastName: string;
  public age: number;
  public actor: Actor;

  public actMovieArray = [];
  public total_pages: number;
  public current_page: number;
  public tempActMovie = [];

  constructor(
    public route: ActivatedRoute,
    public actorService: ActorApiService,
    public movieService: MovieAPIService
  ) {}

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.actorService.getActor(this.id).subscribe(
      (actor) =>  {
        this.actor = actor;
        this.actor.profile_path = this.actorService.formatActorImage(this.actor.profile_path);
      });
    
    this.getMovies();

  }

 
}
