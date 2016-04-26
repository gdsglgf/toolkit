#include <stdio.h>

int main(int argc, char *argv[]) {
    int n, sum = 0;
    while (scanf("%d", &n) != EOF) {
        sum += n;
        printf("n is %d\n", n);
    }
    scanf("%d", &sum);
    printf("sum is %d\n", sum);
    printf("hello ");
    printf("world\n");

    return 0;
}