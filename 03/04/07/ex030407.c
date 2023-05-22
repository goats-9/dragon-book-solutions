s = 0;
for (i = 1; i <= m; i++) {
    while (s > 0 && a[i] != b[s+1]) s = f(s);
    if (a[i] == b[s+1]) s = s + 1;
    if (s == n) return "yes";
}
return "no";