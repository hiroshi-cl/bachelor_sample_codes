import Prelude hiding ((^))
f ^ k = if k == 0 then id
                  else f . (f ^ (k - 1))

data Coaster = Coaster Nat [Nat] deriving Eq

rot k (a : x) = if k == 0 then a : x
                          else rot (k - 1) (x ++ [a])

instance S Coaster where
        next (Coaster k g) = Coaster k (rot (ride k g) g)
        prop (Coaster k g) = sum (take (ride k g) g)

ride k (a : x) = if k >= a then 1 + ride (k - a) x
                           else 0
ride k []      = 0

solve r k n g = psum' r (Coaster k g)

type Nat = Int -- Int で代用
class S s where
    next :: s -> s
    prop :: s -> Nat

nitr' n a = if n < r a then nitr n a
                       else nitr (mod (n - r a) (p a) + r a) a
psum' n a = if n < r a
                then psum n a
                else div (n - r a) (p a) * psum (p a) (nitr (r a) a)
                         + psum (mod (n - r a) (p a)) (nitr (r a) a)
                         + psum (r a) a

nitr n a = (next ^ n) a
psum n a = if n == 0 then 0
                     else prop (nitr (n - 1) a) + psum (n - 1) a
p a = n a - r a
r a = sub 0
    where sub m = if nitr m a == nitr (n a) a then m
                                              else sub (m + 1)
n a = sub 1
    where sub m = if elem (nitr m a) (c a m) then m
                                             else sub (m + 1)
c a m = if m == 0 then []
                  else (nitr (m - 1) a) : c a (m - 1)
