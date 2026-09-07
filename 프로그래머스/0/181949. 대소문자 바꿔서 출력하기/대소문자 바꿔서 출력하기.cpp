#include <iostream>
#include <string>
#include <cctype>
using namespace std;

int main(void) {
    string str;
    cin >> str;
    for (char& ch : str){
        if(islower(ch))
            ch = toupper(ch);
        else
            ch = tolower(ch);
        
    }
    cout << str << endl;
    return 0;
}