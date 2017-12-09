(ns tiny-maze.solver)

; transform a maze of 1s (walls) and 0s (space) to make a path of :x from :S to :E
(defn solve-maze [maze]

  ; get the coordinates of an element, i.e. :S/:E
  (defn coords [e]
    (defn firstHit [list] (count (take-while neg? list)))
    (def x (firstHit (map #(.indexOf % e) maze)))
    (def y (.indexOf (nth maze x) e))
    [x y]
  )

  ; get an element from the maze by its coordinates
  (defn getXY [coll path] (reduce nth path coll))

  ; set the value of an element from the maze by its coordinates, see Ramda's assocPath:
  (defn setXY [coll path x]
    (assoc
      coll
      (nth path 0)
      (if
        (> (count path) 1)
        (setXY
          coll
          (drop 1 path)
          x
        )
        x
      )
    )
  )

  ; get all unchecked neighbors of a tile
  (defn neighbors [x y seen]
    (filter
      #(and
        (contains? maze (nth % 0))
        (contains? (first maze) (nth % 1))
        ((complement contains?) seen %)
        (= 1 (getXY maze %))
      )
      [
        [x (inc y)]
        [x (dec y)]
        [(inc x) y]
        [(dec x) y]
      ]
    )
  )

  ; coordinates of the start and end
  (def start (coords :S))
  (def end (coords :E))

  ; dimensions of the maze
  (def width (count maze))
  (def height (count (first maze)))

  ; generate a filled matrix with the dimensions of the maze
  (defn matrix [x] (repeat width (repeat height x)))

  ; initial distances for each coordinate: all positive infinity
  (def distances (matrix Double/POSITIVE_INFINITY))

  ; algorithm, with state (using atoms)
  (let [
      quit (atom false)
      i (atom 0)
      coords (atom [start])
      seen (atom (set [start]))
      paths (atom (setXY (matrix []) start [start]))
    ]
    ; loop through paths
    (while @quit
      (def xy (nth @coords @i))
      ; append unseen to paths
      (def path (getXY @paths xy))
      (for
        (neighbors xy @seen)
        (fn [tile]
          (swap! coords #(conj % tile))
          (swap! seen #(conj % tile))
          (swap! paths #(setXY % tile (conj path tile)))
          ; quit when found :E
          (swap! quit #(or % (= tile end)))
        )
      )
      ; ++
      (swap! i inc)
    )
    ; reduce paths for :E coords to :x in maze
    (reduce (get paths end) #(setXY %1 %2 :x) maze)
  )
)
