int w, x, y, z;
int i = 3; int j = 4;
{               /* BLOCK 1 */ 
    int i = 5;
    w = i + j;
}
x = i + j;      /* BLOCK 2 */
{               /* BLOCK 3 */
    int j = 6;
    i = 7;
    y = i + j;
}
z = i + j;      /* BLOCK 4 */