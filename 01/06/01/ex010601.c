int w, x, y, z;
int i = 4; int j = 5;
{               /* BLOCK 1 */ 
    int j = 7;
    i = 6;
    w = i + j;
}
x = i + j;      /* BLOCK 2 */
{               /* BLOCK 3 */
    int i = 8;
    y = i + j;
}
z = i + j;      /* BLOCK 4 */