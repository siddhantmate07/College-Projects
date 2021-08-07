/*
Worksop Project - Intelligent Solar Tracking System

Fulesh Chafale   (Roll No. 61, Sec-A)
Gaurav Daga      (Roll No. 62, Sec-A)
Udit Mishra      (Roll No. 79, Sec-A)
Siddhant Mate    (Roll No. 84, Sec-A)

Guided By - Dr. S. B. Pokle & Prof. Divya Shrivastava
*/

#include <Stepper.h>

const int steps_per_revolution = 200;  

int speed = 10;

// initializing the stepper library on pins 8 to 11:
Stepper stepper(steps_per_revolution, 8, 9, 10, 11);

int date = 1;         // Date input here
int month = 1;
int year = 2021;
int d = 0;

float latitude = 21.146;    // latitude input here, Nagpur

void setup() 
{
  // setting the speed at 1/600 rpm:
  stepper.setSpeed(speed);

  d = day_of_year(date,month,year);
  
  // initializing the serial port:
  Serial.begin(9600);
}

void loop()
{
  Serial.print("Day = ");
  Serial.println(d);
  float delta = 0,alpha,start_angle,end_angle,i;
  
  if(d>=1&&d<=79)
  {
    delta=-22.98+0.2634*d;
  }
  else if(d>79&&d<=172)
  {
    delta=0.2634*(d-81);
  }
  else if(d>172&&d<=266)
  { 
    delta=23.45-0.2634*(d-172);
  }
  else if(d>266&&d<=356)
  {      
    delta=-0.2634*(d-266);
  }
  
  alpha=90+latitude-delta;
  
  if(alpha-8*15>0)
  {
    start_angle=alpha-8*15 ;
  }
  else if(alpha-7*15>0)
  {
    start_angle=alpha-7*15 ;
  }
  else
  {
    start_angle=alpha-6*15;
  }

  Serial.print("Start angle = ");
  Serial.println(start_angle);
  
  if( (alpha+4*15) > 180 )
  {
    end_angle=alpha+3*15 ; 
  }
  else
  {
    end_angle=alpha+4*15 ;
  }

  Serial.print("End angle = ");
  Serial.println(end_angle);
  Serial.println();
  
  i=start_angle;
  
  while(i<end_angle)
  {
    stepper.step(steps_per_revolution/20);
    Serial.print("Position = ");
    Serial.println(i);
    i=i+10;
    delay(1000);
  }
  Serial.println();
  while(i>start_angle)
  {
    stepper.step(-steps_per_revolution/20);
    Serial.print("Position = ");
    Serial.println(i);
    i=i-5;
    delay(200);
  }
  
  d++;
  if(d==366)
  {
    d=1;
  }
    Serial.println();
    delay(3000);
}

int day_of_year(int date,int month,int year)
{
  int days_in_feb=28,doy=date; 

    // checking for leap year
    if((year%4==0 && year%100!=0)||(year%400==0))
    {
        days_in_feb=29;
    }

    switch(month)
    {
      case 1:
        break;
      case 2:
          doy += 31;
          break;
      case 3:
          doy += 31+days_in_feb;
          break;
      case 4:
          doy += 31+days_in_feb+31;
          break;
      case 5:
          doy += 31+days_in_feb+61;
          break;
      case 6:
          doy += 31+days_in_feb+92;
          break;
      case 7:
          doy += 31+days_in_feb+122;
          break;            
      case 8:
          doy += 31+days_in_feb+153;
          break;
      case 9:
          doy += 31+days_in_feb+184;
          break;
      case 10:
          doy += 31+days_in_feb+214;            
          break;            
      case 11:
          doy += 31+days_in_feb+245;            
          break;                        
      case 12:
          doy += 31+days_in_feb+275;            
          break;                                    
    }
    
    return doy;
}
