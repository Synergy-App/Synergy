package com.sungkyul.synergy.utils

import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.client.OpenAI
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.model.ModelId
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/** chatGPT API 셋팅 */
/*
    [References]
    - https://github.com/Aallam/openai-kotlin
*/
class OpenAIAPI {
    // 자신의 API Key
    private val openAIAPIKey: String = ""
    // ChatGPT의 응답을 기다리는 시간. 만일 시간이 초과되면 오류가 발생한다.
    private val duration: Duration = 60.seconds
    // 모델 ID
    private val modelID: String = "gpt-3.5-turbo"

    private val openAI: OpenAI = OpenAI(
        token = openAIAPIKey,
        timeout = Timeout(socket = duration)
    )

    /*
    ChatGPT에게 메시지를 보내고 답변을 받는다.

    [Parameters]
    - system: ChatGPT의 역할
    - user: ChatGPT에게 보낼 메시지
    - maxTokens: 최대 토큰 수

    [Return Value]
    ChatGPT의 답변을 반환한다. 만일 답변이 없으면 null을 반환한다.
    */
    suspend fun chat(system: String, user: String, maxTokens: Int? = null): String? {
        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId(modelID),
            messages = listOf(
                ChatMessage(
                    role = ChatRole.System,
                    content = system
                ),
                ChatMessage(
                    role = ChatRole.User,
                    content = user
                )
            ),
            maxTokens = maxTokens
        )

        val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)
        return completion.choices[0].message.content
    }
}
