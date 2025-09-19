class Solution {
    public boolean lemonadeChange(int[] bills) {
               int five = 0; 
               int ten = 0;

        for (int i = 0; i < bills.length; i++) {
            // Case 1: customer gives $5
            if (bills[i] == 5) {
                five++;
            } 
            // Case 2: customer gives $10
            else if (bills[i] == 10) {
                if (five > 0) {
                    five--; // give back $5
                    ten++;  // we now have one $10
                } else {
                    return false; // cannot give change
                }
            } 
            // Case 3: customer gives $20
            else {
                if (ten > 0 && five > 0) {
                    // better to give $10 + $5
                    ten--;
                    five--;
                } else if (five >= 3) {
                    // otherwise give three $5 bills
                    five -= 3;
                } else {
                    return false; // cannot give change
                }
            }
        }
        return true;

    }
}