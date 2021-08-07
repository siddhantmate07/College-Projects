#include <stdio.h>
#include <math.h>
#include <Windows.h>

int main(void)
{
    int date, month, year, d;
    float latitude;
    
    date = 28;
    month = 4;
    year = 2021;
    
    latitude = 21.146;
    
    d = day_of_year(date, month, year);
    
    solar_calculations( d , latitude);
    
    return 0;
}

void solar_calculations(int d, float latitude)
{    
    float delta, alpha, start_angle, end_angle, i = 10;
       
    while(1)
    {
    	printf("\n\nDay = %d",d); 
    	
    	if ( d >=1 && d <= 79 )
    		delta = -22.98 + 0.2634*d;
		else if ( d > 79 && d <= 172 )
    		delta = 0.2634*(d-81);
		else if ( d > 172 && d <= 266 )
    		delta = 23.45 - 0.2634*(d-172);
		else if ( d > 266 && d <= 356 )
    		delta = -0.2634*(d-266);
		
		printf("\ndelta = %f\n",delta); 

    	alpha = 90 + latitude - delta ;  // elevation angle at local noon
    	printf("alpha = %f\n",alpha); 
    	
    	if ( alpha - 6*15 > 10 )
    		start_angle = alpha - 6*15 ;
		else if ( alpha - 5*15 > 10 )
			start_angle = alpha - 5*15 ;
    	else
    		start_angle = alpha - 4*15 ;
		
		printf("start_angle = %f\n",start_angle); 
    	
    	if ( alpha + 6*15 < 175 )
    		end_angle = alpha + 6*15 ; 
    	else if ( alpha + 5*15 < 175 )
    		end_angle = alpha + 5*15 ; 
		else
    		end_angle = alpha + 4*15 ; 
    		
		printf("end_angle = %f\n\n",end_angle);   
    	
    	i = start_angle;
    	
    	while( i < end_angle )					// east to west
		{
			printf("Motor angle = %f\n",i);
			i = i + 10;							// will change
			sleep(1);							// will change
		}
		
		printf("\n");

		while( i > start_angle )				// init1al position 
		{
			printf("Motor angle = %f\n",i);
			i-- ;
			sleep(1/100);
		}
		
		sleep(3);
		
    	d++;
    	if ( d == 366 )
    		d = 1; 
	}
	
}

int day_of_year(int date, int month, int year)
{
	int days_in_feb = 28, doy = date; 

    // checking for leap year
    if( (year % 4 == 0 && year % 100 != 0 ) || (year % 400 == 0) )
    {
        days_in_feb = 29;
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

