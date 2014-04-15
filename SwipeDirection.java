package com.sukohi.lib;

/*  Dependency: DisplaySize  */

import android.content.Context;
import android.graphics.Point;
import android.view.MotionEvent;

public class SwipeDirection {

	public static final int DIRECTION_STAY = 0;
	public static final int DIRECTION_UP = 1;
	public static final int DIRECTION_RIGHT = 2;
	public static final int DIRECTION_DOWN = 3;
	public static final int DIRECTION_LEFT = 4;
	private float x1, x2, y1, y2, xDifferences, yDifferences;
	private float minDifferences;
	private Point displaySize;
	
	public SwipeDirection(Context context, float minDifferences) {
		
		displaySize = DisplaySize.get(context);
		this.minDifferences = minDifferences;
		
	}
	
	public int get(MotionEvent event) {
		
		int direction = DIRECTION_STAY;
		
		switch(event.getAction()) {
		
		case(MotionEvent.ACTION_DOWN):
			
			x1 = event.getX();
			y1 = event.getY();
			break;
			
		case(MotionEvent.ACTION_MOVE):
			
			x2 = event.getX();
			y2 = event.getY();
			xDifferences = x2 - x1;
			yDifferences = y2 - y1;
			float absoluteX = Math.abs(xDifferences);
			float absoluteY = Math.abs(yDifferences);
	
			if(absoluteX > absoluteY) {
				
				if(absoluteX < displaySize.x * minDifferences) {
					
					return DIRECTION_STAY;
					
				}
				
				if(xDifferences > 0) {
					
					direction = DIRECTION_RIGHT;
					
				} else {
					
					direction = DIRECTION_LEFT;
					
				}
				
			} else {

				if(absoluteY < displaySize.y * minDifferences) {
					
					return DIRECTION_STAY;
					
				}
				
				if(yDifferences > 0) {
					
					direction = DIRECTION_DOWN;
					
				} else {
					
					direction = DIRECTION_UP;
					
				}
				
			}
			break;
			
		}
		
		return direction;
		
	}
	
}
/*** Example

	new SwipeDirection(this, 0.05F);
	int direction = swipeDirection.get(event);
	
	case SwipeDirection.DIRECTION_UP:
		// something...
		break;
		
	case SwipeDirection.DIRECTION_RIGHT:
		// something...
		break;
		
	case SwipeDirection.DIRECTION_DOWN:
		// something...
		break;
		
	case SwipeDirection.DIRECTION_LEFT:
		// something...
		break;
		
	}
***/
