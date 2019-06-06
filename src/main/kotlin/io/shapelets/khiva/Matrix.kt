/*
 * Copyright (c) 2019 Shapelets.io
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package io.shapelets.khiva

/**
 * Khiva Matrix Profile class containing matrix profile methods.
 */
object Matrix : Library() {
    @JvmStatic
    private external fun mass(query: Long, tss: Long): LongArray

    @JvmStatic
    private external fun findBestNOccurrences(query: Long, tss: Long, n: Long): LongArray

    @JvmStatic
    private external fun stomp(a: Long, b: Long, m: Long): LongArray

    @JvmStatic
    private external fun stompSelfJoin(a: Long, m: Long): LongArray

    @JvmStatic
    private external fun findBestNMotifs(profile: Long, index: Long, m: Long, n: Long, selfJoin: Boolean): LongArray

    @JvmStatic
    private external fun findBestNDiscords(profile: Long, index: Long, m: Long, n: Long, selfJoin: Boolean): LongArray

    /**
     * Mueen's Algorithm for Similarity Search.
     *
     * The result has the following structure:
     * - 1st dimension corresponds to the index of the subsequence in the time series.
     * - 2nd dimension corresponds to the number of queries.
     * - 3rd dimension corresponds to the number of time series.
     *
     * For example, the distance in the position (1, 2, 3) correspond to the distance of the third query to the fourth time
     * series for the second subsequence in the time series.
     *
     * [1] Chin-Chia Michael Yeh, Yan Zhu, Liudmila Ulanova, Nurjahan Begum, Yifei Ding, Hoang Anh Dau, Diego Furtado Silva,
     * Abdullah Mueen, Eamonn Keogh (2016). Matrix Profile I: All Pairs Similarity Joins for Time Series: A Unifying View
     * that Includes Motifs, Discords and Shapelets. IEEE ICDM 2016.
     *
     * @param query Array whose first dimension is the length of the query time series and the second dimension is the
     *              number of queries.
     * @param tss   Array whose first dimension is the length of the time series and the second dimension is the number of
     *              time series.
     * @return Array with the distances.
     */
    fun mass(query: Array, tss: Array): Array {
        val refs = mass(query.reference, tss.reference)
        query.reference = refs[0]
        tss.reference = refs[1]
        return Array(refs[2])
    }


    /**
     * Calculates the N best matches of several queries in several time series.
     *
     * The result has the following structure:
     * - 1st dimension corresponds to the nth best match.
     * - 2nd dimension corresponds to the number of queries.
     * - 3rd dimension corresponds to the number of time series.
     *
     * For example, the distance in the position (1, 2, 3) corresponds to the second best distance of the third query in the
     * fourth time series. The index in the position (1, 2, 3) is the is the index of the subsequence which leads to the
     * second best distance of the third query in the fourth time series.
     *
     * @param query Array whose first dimension is the length of the query time series and the second dimension is the
     *              number of queries.
     * @param tss   Array whose first dimension is the length of the time series and the second dimension is the number of
     *              time series.
     * @param n     Number of matches to return.
     * @return Array or arrays with the distances and indexes.
     */
    fun findBestNOccurrences(query: Array, tss: Array, n: Long): kotlin.Array<Array> {
        val refs = findBestNOccurrences(query.reference, tss.reference, n)
        query.reference = refs[0]
        tss.reference = refs[1]
        return arrayOf(Array(refs[2]), Array(refs[3]))
    }


    /**
     * STOMP algorithm to calculate the matrix profile between 'arrA' and 'arrB' using a subsequence length
     * of 'm'.
     *
     * (1) Yan Zhu, Zachary Zimmerman, Nader Shakibay Senobari, Chin-Chia Michael Yeh, Gareth Funning, Abdullah Mueen,
     * Philip Brisk and Eamonn Keogh (2016). Matrix Profile II: Exploiting a Novel Algorithm and GPUs to break
     * the one Hundred Million Barrier for Time Series Motifs and Joins. IEEE ICDM 2016.
     *
     * @param arrA Array containing the input time series.
     * @param arrB Array containing the input time series.
     * @param m    Long with the subsequence length.
     * @return Array of arrays with the Matrix profile and index.
     */
    fun stomp(arrA: Array, arrB: Array, m: Long): kotlin.Array<Array> {
        val refs = stomp(arrA.reference, arrB.reference, m)
        arrA.reference = refs[0]
        arrB.reference = refs[1]
        return arrayOf(Array(refs[2]), Array(refs[3]))
    }

    /**
     * STOMP algorithm to calculate the matrix profile between 't' and itself using a subsequence length
     * of 'm'. This method filters the trivial matches.
     *
     * (1) Yan Zhu, Zachary Zimmerman, Nader Shakibay Senobari, Chin-Chia Michael Yeh, Gareth Funning, Abdullah Mueen,
     * Philip Brisk and Eamonn Keogh (2016). Matrix Profile II: Exploiting a Novel Algorithm and GPUs to break
     * the one Hundred Million Barrier for Time Series Motifs and Joins. IEEE ICDM 2016.
     *
     * @param arr Array containing the input time series.
     * @param m   Long with the subsequence length.
     * @return Array of arrays with the Matrix profile and index.
     */
    fun stompSelfJoin(arr: Array, m: Long): kotlin.Array<Array> {
        val refs = stompSelfJoin(arr.reference, m)
        arr.reference = refs[0]
        return arrayOf(Array(refs[1]), Array(refs[2]))
    }

    /**
     * This function extracts the best N motifs from a previously calculated matrix profile.
     *
     * @param profile  The matrix profile containing the minimum distance of each subsequence.
     * @param index    The matrix profile index.
     * @param m        Subsequence length value used to calculate the input matrix profile.
     * @param n        Number of discords to extract.
     * @param selfJoin Indicates whether the input profile comes from a self join operation or not. It determines
     * whether the mirror similar region is included in the output or not.
     * @return Array of arrays with the distances, the indices and the indices in the compared time series.
     */
    fun findBestNMotifs(profile: Array, index: Array, m: Long, n: Long, selfJoin: Boolean = false): kotlin.Array<Array> {
        val refs = findBestNMotifs(profile.reference, index.reference, m, n, selfJoin)
        profile.reference = refs[0]
        index.reference = refs[1]
        return arrayOf(Array(refs[2]), Array(refs[3]), Array(refs[4]))
    }

    /**
     * This function extracts the best N discords from a previously calculated matrix profile.
     *
     * @param profile  The matrix profile containing the minimum distance of each subsequence.
     * @param index    The matrix profile index
     * @param m        Subsequence length value used to calculate the input matrix profile.
     * @param n        Number of motifs to extract.
     * @param selfJoin Indicates whether the input profile comes from a self join operation or not. It determines
     * whether the mirror similar region is included in the output or not.
     * @return Array of arrays with the distances, the indices and the indices in the compared time series.
     */
    fun findBestNDiscords(profile: Array, index: Array, m: Long, n: Long, selfJoin: Boolean = false): kotlin.Array<Array> {
        val refs = findBestNDiscords(profile.reference, index.reference, m, n, selfJoin)
        profile.reference = refs[0]
        index.reference = refs[1]
        return arrayOf(Array(refs[2]), Array(refs[3]), Array(refs[4]))
    }
}
