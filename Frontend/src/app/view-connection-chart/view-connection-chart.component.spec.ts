import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ViewConnectionChartComponent } from './view-connection-chart.component';


describe('ViewConnectionChartComponent', () => {
  let component: ViewConnectionChartComponent;
  let fixture: ComponentFixture<ViewConnectionChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewConnectionChartComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewConnectionChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
