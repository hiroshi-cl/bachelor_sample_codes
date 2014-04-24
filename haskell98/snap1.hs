import Prelude hiding (map)

data Snapper = OFF | ON deriving Eq
solve n k = if chk (sim n k) then "ON"
                             else "OFF"
chk x = all (== ON) x

sim n k = map proj (ntob n k)
proj 0 = OFF
proj 1 = ON
map f (a : x) = (f a) : (map f x)
map f []      = []
ntob n k = if n == 0 then []
                     else (mod k 2) : (ntob (n - 1) (div k 2))
