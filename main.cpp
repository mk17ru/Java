#include <iostream>
#include <cstring>

int main(int argc, char* argv[]) {
    if (argc < 2) {
        return std::cout << "cannot find string for searching", 0;
    }
    if (argc < 3) {
        return std::cout << "cannot find filename", 0;
    }
    char * target = argv[1];
    char * file = argv[2];
    int32_t n = strlen(argv[1]);
    char buffer[8192];

    FILE* f = std::fopen(file, "r");

    while (!feof(f)) {
        fscanf(f, "%s", buffer);
        kek: for (int begin = 0; begin < 8192; ++begin) {
            bool res = true;
            for (int pnt = 0; pnt < ((std::string)target).length() && begin+pnt < 8192; ++pnt) {
                if (target[pnt] != buffer[begin+pnt]) {
                    res = false;
                    break;
                }
            }
            ///

        }

    }

    std::cout << "false";
    return 0;
}
