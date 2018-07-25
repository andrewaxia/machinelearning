package com.insight.ml

import com.insight.utils.Statistic

import scala.collection.mutable.ListBuffer
import scala.Option
import scala.util.control.Breaks._

class KMeans(val k: Int, val iterMaxTimes: Int) extends KMeansTrait with EuclideanDistance {

  override def run(dataSet: List[Vector[Double]]): List[Cluster[Double]] = {
    val clusters = initialize(dataSet)
    if (clusters.isEmpty) List.empty
    else {
      val memberShip = Array.fill(dataSet.size)(0)
      assignToCluster(dataSet, clusters, memberShip)
      updateClusters(iterMaxTimes, dataSet, clusters, memberShip)
    }
  }

  def initialize(dataSet: List[Vector[Double]]): List[Cluster[Double]] = {
    val maxStdDevDim = (0 to dataSet.transpose.size - 1).maxBy(
      dataSet.transpose.map(l => {
        val stats = Statistic(l.toVector);
        val stdDev = stats.stdDev()
        stdDev
      }))
    val sortedObservations = dataSet.zipWithIndex.map(v => {
      (v._1(maxStdDevDim), v._2)
    }).sortWith(_._1 < _._1)

    val halfSegSize = ((sortedObservations.size >> 1) / k).floor.toInt;
    val initialMeans = sortedObservations.filter(v => {
      (v._2 % halfSegSize) == 0 && v._2 % (halfSegSize << 1) == 0
    }).map(n => dataSet(n._2))

    val initialClusters = initialMeans.foldLeft(ListBuffer[Cluster[Double]]())((listBuffer, v) => {
      listBuffer.append(Cluster[Double](v))
      listBuffer
    })
    initialClusters.toList
  }

  def assignToCluster(dataSet: List[Vector[Double]], clusters: List[Cluster[Double]], memberShip: Array[Int]): Int = {
    val reassignedSize = dataSet.zipWithIndex.count(v => {
      val closestCluster = getClosestCluster(v._1, clusters)
      val reassigned = memberShip(v._2) != closestCluster
      clusters(closestCluster) += v._2
      memberShip(v._2) = closestCluster
      reassigned
    })
    reassignedSize
  }

  def getClosestCluster(observation: Vector[Double], clusters: List[Cluster[Double]]): Int = {
    val closestClusterIndex = (0 to clusters.size - 1).minBy(clusters.map(c => {
      val dist = distance(c.centroid.toVector, observation)
      dist
    }))
    closestClusterIndex
  }

  def updateClusters(iterTimes: Int, dataSet: List[Vector[Double]], initClusters: List[Cluster[Double]], memberShip: Array[Int]): List[Cluster[Double]] = {
    var newClusters: List[Cluster[Double]] = List.empty;
    breakable {
      for (i <- 0 to iterTimes) {
        newClusters = initClusters.map(
          cluster => {
            if (cluster.size > 0)
              cluster.calibrateCentroid(dataSet.toList)
            else {
              initClusters.filter(_.size > 0)
                .maxBy(_.stdDev(dataSet, distance))
            }
          })
        val reassignedSize = assignToCluster(dataSet, newClusters, memberShip)
        if (reassignedSize == 0) break()
      }
    }
    newClusters
  }
}

object KMeans {
  def apply(k: Int, iter: Int, distance: Distance): KMeans = new KMeans(k, iter)
}