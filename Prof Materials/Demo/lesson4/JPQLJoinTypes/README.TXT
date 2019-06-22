
Exercises 1-Many fetch  scenario 
OPERATES on Member  == see MemberDaoImpl

Join FETCH is inner Join [see findAllJoinFetch()]
LEFT Join FETCH is Left Outer Join [see findAllOuterJoinFetch]

This ONE is in AddressDaoImpl:
LEFT Join FETCH ON ADDRESS is RIGHT Outer Join [see findAllOuterJoinFetch in Address]

Join fetch removes N+1 but introduces duplicates " Cartesian Product"
Can "remove" duplicates by using DISTINCT

