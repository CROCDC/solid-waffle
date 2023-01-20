package com.crocdc.domain.model

import com.crocdc.datadatabase.model.LearnedAt

data class Move(val name: String, val learnedAt: List<LearnedAt>)
