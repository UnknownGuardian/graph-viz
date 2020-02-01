mkdir -p ../data/simple
mkdir -p ../data/adjacency_matrix

for index in {2..9}
do
  ../tools/nauty/nauty26r12/geng $index -q > ../data/simple/$index.g6
  ../tools/showg -a ../data/simple/$index.g6 > ../data/adjacency_matrix/$index.g6
done
