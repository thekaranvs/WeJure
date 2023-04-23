(ns wejure.components.searchPage
  (:require ["../js/profile" :as profile]
            [reagent-mui.material.box :refer [box]]
            [reagent-mui.material.button :refer [button]]
            [reagent-mui.material.paper :refer [paper]]
            [reagent-mui.material.avatar :refer [avatar]]
            [reagent-mui.material.typography :refer [typography]]
            [reitit.frontend.easy :as reitit-fe]
            [reagent.core :as r]))

(def result-list (r/atom []))

(def ipfs-url "https://ipfs.io/ipfs/")

(defn ^:export vector-append [target-atom value]
  (swap! target-atom conj value))

(defn ^:export vector-prepend [target-atom value]
  (reset! target-atom (into (vector value) @target-atom)))

(defn search-page [{{:keys [search-input]} :path-params}]
  (when (= 0 (count @result-list))
    (profile/searchUser search-input))
  [:div
   {:style {:margin "25px 20% 25px 20%"}}
   [typography
    {:variant "h5"
     :component "div"}
    "Search result for: " search-input]
   [paper {:id "search-result-box"
           :variant "outlined"
           :sx {:height 800 :my 2 :overflow "auto"}}
    (for [result (js->clj @result-list :keywordize-keys true)] 
      ^{:key result} 
      [box {:sx {:display "flex" :box-shadow 1}}
       [avatar {:sx {:mx 4 :my 2 :width 48 :height 48}
                :src (str ipfs-url (get result :iconCID))}]
       [typography {:sx {:mx 2 :my 3 :font-size "20px"}}
        (get result :username)]
       [box {:style {:display "flex" :flex-grow 1 :justify-content "flex-end"}}
        [button {:sx {:my 3 :mr 4 :width 80 :height 35}
                 :variant "contained"
                 :href (reitit-fe/href :wejure.core/user {:username (get result :username)})}
         "Profile"]]]   
    )]])