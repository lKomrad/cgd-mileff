package game;

import engine.Vector2D;
import common.Unit;

public class GameLogic {
	
	
	
	
	
	
	
	public static float calculateDistance(Unit unit1, Unit unit2) {
		Vector2D vec1 = unit1.GetPosition();
		Vector2D vec2 = unit2.GetPosition();
		return squareRoot(((vec1.x-vec2.x) * (vec1.x-vec2.x) + (vec1.y-vec2.y) * (vec1.y-vec2.y)), 1f);
	}
	
	public static float calculateDistance(Vector2D vec1, Vector2D vec2) {
		return squareRoot(((vec1.x-vec2.x) * (vec1.x-vec2.x) + (vec1.y-vec2.y) * (vec1.y-vec2.y)), 1f);
	}
	
	
	
	static float squareRoot(float number, float precision) 
    { 
		float start = 0, end = number; 
		float mid; 
  
        // variable to store the answer 
		float ans = 0.0f; 
  
        // for computing integral part 
        // of square root of number 
        while (start <= end)  
        { 
            mid = (start + end) / 2; 
              
            if (mid * mid == number)  
            { 
                ans = mid; 
                break; 
            } 
  
            // incrementing start if integral 
            // part lies on right side of the mid 
            if (mid * mid < number) { 
                start = mid + 1; 
                ans = mid; 
            } 
  
            // decrementing end if integral part 
            // lies on the left side of the mid 
            else { 
                end = mid - 1; 
            } 
        } 
  
        // For computing the fractional part 
        // of square root upto given precision 
        float increment = 0.1f; 
        for (int i = 0; i < precision; i++) { 
            while (ans * ans <= number) { 
                ans += increment; 
            } 
  
            // loop terminates when ans * ans > number 
            ans = ans - increment; 
            increment = increment / 10; 
        } 
        return (float)ans; 
    } 
}
