import { Component, OnInit } from '@angular/core';
import { AuditService } from 'src/app/Audit/Services/audit.service';
import { Batch } from 'src/app/Batch/type/batch';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent implements OnInit {
  batches: Batch[];
  weeks = [];
  selectedWeek: number;
  selectedBatch: Batch;
  selectedBatches: Batch[];

  constructor() { }

  ngOnInit() {   
    this.selectedWeek=1;
  }

  showActiveWeek(week: number) {
    if (week==this.selectedWeek) {
      return "active";
    }
  }

  selectWeek(event: number) {
    this.selectedWeek = event;
    this.auditService.selectedWeek = event;
  }

  addWeek() {
    var last = this.weeks[this.weeks.length-1];
    this.weeks.push(last+1);
    this.selectedWeek=last+1;
    this.selectedBatch.weeks++;
    console.log(this.selectedBatch.batchId);
    this.auditService.putBatch(this.selectedBatch).subscribe(result => {
      console.log('updated');
    });
  }
  
  getWeeks() {
    this.weeks = [];
    for(var i = 0; i<this.selectedBatch.weeks; i++){
      this.weeks.push(i+1);
    }
  }


}
