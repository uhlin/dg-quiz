#include <cstdio>
#include <cstring>
#include <random>

#ifndef nitems
#define nitems(_a) (sizeof((_a)) / sizeof((_a)[0]))
#endif

char url[32] = "";

int
main()
{
	const char legal_index[] =
	    "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	    "abcdefghijklmnopqrstuvwxyz";

	std::random_device rd;
	std::mt19937 gen(rd());
	std::uniform_int_distribution<size_t> dist(0, strlen(legal_index) - 1);

	for (size_t i = 0; i < nitems(url); i ++)
		url[i] = legal_index[dist(gen)];

	std::string s(url);
	s.append("_");
	s.append("AdminView");
	puts(s.c_str());
	return 0;
}
