# Machine Learning Algorithms

## Supervised Learning Algorithms
Both K-nearest neighbor and Perceptron algorithms parse the training and test data into an ArrayList of arrays (ArrayList<Double[]>). This is used to extend the algorithm for any number of data tuples and attributes. In KNN, for each data tuple of the test data, its distance from the training data tuples is calculated. The distance and corresponding output value (0 or 1) is stored in a separate ArrayList. A list of minimum distances from this distance ArrayList is then formed (if more than one point at same distance). A HashMap is used to store the minimum distances with their outputs and the nearest neighbor is randomly selected from the nearest points. This algorithm is almost 100% accurate but might return inaccurate result due to random selection. In Perceptron, Learning Rate and Threshold are used to calculate the output. After parsing the test data, weights are randomly initialized. The output is calculated and the weights are adjusted as per the error (actual output - calculated output). To achieve higher accuracy, the Learning Rate is gradually decreased. Since the data is not linearly separable, the algorithm thrashes and gives inaccurate results. Best results are obtained when the the learning rate is set to 0.005 and reduced by a factor of 0.05. In ID3 implementation, the training data is normalized in the range of 1 to 10. A Tree class is defined to build the decision tree from the training data. Attribute class is used to classify the data into bins. These bins are [0-3], [3-7] and [7,10]. Enumeration is used to implement binning. A tree Node consists of parent, children, data and entropy. The main algorithm first creates a new tree, builds it with the training set and traverses it with the test data.

## Unsupervised Learning Algorithms
In K means and Agglomerative Nesting, the data is parsed into an ArrayList<Double[]>. The data is normalized in the range of 0 to 1 so that a single large attribute doesn’t affect the entire clustering. The centroids, which are the first k points of the data (k is the number of clusters), are stored using the same data structure. Two separate structures are used to store the old and new centroids. The clusters are stored in a three dimensional data structure (ArrayList<ArrayList<Double[]>>). This enables the clusters to have an index denoting the cluster and the data which is an ArrayList. The distance of the point with all the centroids is calculated and the point is inserted in to the cluster assigned to the nearest centroid. The distance between points is calculated using Manhattan Distance. The centroids are again calculated and the process is repeated until the old and new centroids are equal. In AGNES, the distance of a point is from all other data points is calculated. The nearest point is chosen as in the KNN algorithm and these two points are stored in the three dimensional data structure (ArrayList<ArrayList<Double[]>>) as one cluster and removed from the data set. Similarly, the distance of one cluster is calculated with other cluster using single linkage clustering. The two nearest clusters are combined to form one cluster and added to the cluster data structure. This process is continued till we have only one cluster.